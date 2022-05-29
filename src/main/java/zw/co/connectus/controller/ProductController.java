package zw.co.connectus.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.connectus.dal.entity.Product;
import zw.co.connectus.dal.entity.User;
import zw.co.connectus.dal.entity.UserProductRating;
import zw.co.connectus.dal.repository.ProductRepository;
import zw.co.connectus.dal.repository.UserProductRatingRepository;
import zw.co.connectus.service.UserServiceImpl;
import zw.co.connectus.service.mapper.DtoMapper;
import zw.co.connectus.service.model.CreateProductDto;
import zw.co.connectus.service.model.UserDto;
import zw.co.connectus.util.ResponseDto;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

    static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserProductRatingRepository userProductRatingRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    DtoMapper mapper;

    @PostMapping
    public Product createProduct(@RequestBody CreateProductDto createProductDto) {
        return productRepository.save(mapper.map(createProductDto));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ResponseDto> delete(@PathVariable("productId") UUID productId) {
        productRepository.deleteById(productId);
        return ResponseEntity.ok(new ResponseDto("success", "Successfully deleted!", null));
    }

    @GetMapping("/user/{userId}")
    public List<Product> findAllByUserId(@PathVariable("userId") UUID userId) {
        return productRepository.findAllByUserId(userId.toString());
    }

    @GetMapping("/recommended/user/{userId}")
    public ResponseEntity<List<Product>> findRecommendedProducts(@PathVariable("userId") UUID userId, @RequestParam("lat") double lat, @RequestParam("lng") double lng) {
        Optional<User> byId = userService.findById(userId);
        if (byId.isPresent()) {
            User user = byId.get();
            List<Product> allByUserIdNot = productRepository.findAllByUserIdNot(userId.toString());

            // filter products out of range and sort by rating
            List<Product> collect1 = allByUserIdNot.stream()
                    .filter(product -> filterProximity(user, lat, lng, product))
                    .sorted(Comparator.comparing(Product::getRating))
                    .collect(Collectors.toList());
            if (collect1.size() <= 3) {
                return ResponseEntity.ok(collect1);
            }
            List<String> disliked = userProductRatingRepository.findAllByUserIdAndLikedIsFalse(userId.toString())
                    .stream().map(UserProductRating::getProductId).collect(Collectors.toList());
            collect1 = collect1.stream().filter(product -> isNotDisliked(product, disliked)).collect(Collectors.toList());
            return ResponseEntity.ok(collect1);
        }
        return ResponseEntity.notFound().build();
    }

    private boolean isNotDisliked(Product product, List<String> disliked) {
        String productId = product.getId().toString();
        if (disliked.contains(productId)) {
            return false;
        }
        return true;
    }


    private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    private boolean filterProximity(User user, double lat, double lng, Product product) {
        try {
            double k = distance(lat, lng, product.getLat(), product.getLng(), 'K');
            if (k <= user.getGeofenceRange()) {
                return true;
            }
        } catch (Exception ignore) {
        }
        return false;
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
