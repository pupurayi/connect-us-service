package zw.co.connectus.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.connectus.dal.repository.ProductRepository;

@Service
public class RatingServiceImpl {

    static Logger logger = LoggerFactory.getLogger(RatingServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;


}
