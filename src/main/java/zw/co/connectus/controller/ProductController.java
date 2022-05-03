package zw.co.connectus.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/user/{userId}")
    public List<Product> findAllByUserId(@PathVariable("userId") UUID userId) {
        return productRepository.findAllByUserId(userId);
    }

    @GetMapping("/recommended/user/{userId}")
    public List<Product> findRecommendedProducts(@PathVariable("userId") UUID userId) {
        return productRepository.findAll();
    }
}
