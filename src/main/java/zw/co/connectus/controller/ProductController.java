package zw.co.connectus.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.connectus.dal.entity.Product;
import zw.co.connectus.dal.repository.ProductRepository;
import zw.co.connectus.service.mapper.DtoMapper;
import zw.co.connectus.service.model.CreateProductDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

    static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    DtoMapper mapper;

    @PostMapping
    public Product createProduct(@RequestBody CreateProductDto createProductDto) {
        return productRepository.save(mapper.map(createProductDto));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> delete(@PathVariable("productId") UUID productId) {
        productRepository.deleteById(productId);
        return ResponseEntity.ok("Successfully deleted product!");
    }

    @GetMapping("/user/{userId}")
    public List<Product> findAllByUserId(@PathVariable("userId") UUID userId) {
        return productRepository.findAllByUserId(userId.toString());
    }

    @GetMapping("/recommended/user/{userId}")
    public List<Product> findRecommendedProducts(@PathVariable("userId") UUID userId) {
        return productRepository.findAll();
    }

    @GetMapping("/search/{userId}")
    public List<Product> searchProducts(@PathVariable("userId") UUID userId, @RequestParam("category") String category, @RequestParam("name") String name, @RequestParam("lat") double lat, @RequestParam("lng") String lng, @RequestParam("sortBy") String sortBy) {
        return productRepository.findAll();
    }

    @GetMapping("/rating/user/{userId}")
    public List<Product> getProductsForUserRating(@PathVariable("userId") UUID userId) {
        // least rated products
        // todo rating syncer scheduled 1 hour
        return productRepository.findAll().subList(0, 2);
    }
}
