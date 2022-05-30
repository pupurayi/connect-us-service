package zw.co.connectus.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import zw.co.connectus.dal.entity.Order;
import zw.co.connectus.dal.entity.Product;
import zw.co.connectus.dal.entity.UserProductRating;
import zw.co.connectus.dal.repository.OrderRepository;
import zw.co.connectus.dal.repository.ProductRepository;
import zw.co.connectus.dal.repository.UserProductRatingRepository;

import java.util.List;

@Service
public class RatingServiceImpl {

    static Logger logger = LoggerFactory.getLogger(RatingServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserProductRatingRepository userProductRatingRepository;

    @Scheduled(fixedDelay = 3600 * 1000)
    public void scheduledRatingManager() {
        List<Product> allProducts = productRepository.findAll();
        for (Product product : allProducts) {
            calculateRating(product);

        }
    }

    public double calculateRating(Product product) {
        return 0;
    }

}
