package zw.co.connectus.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.connectus.dal.entity.Order;
import zw.co.connectus.dal.repository.OrderRepository;
import zw.co.connectus.service.UserServiceImpl;
import zw.co.connectus.service.model.CheckDto;
import zw.co.connectus.service.model.SignInDto;
import zw.co.connectus.service.model.UserDto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping(path = "/check/{msisdn}")
    public CheckDto check(@PathVariable("msisdn") String msisdn) {

        return userService.check(msisdn);
    }

    @PostMapping(path = "/sign-in")
    public ResponseEntity<UserDto> signIn(@RequestBody SignInDto signInDto) {

        return userService.signIn(signInDto);
    }

    @GetMapping("/reset-password/{msisdn}")
    public ResponseEntity resetPassword(@PathVariable("msisdn") String msisdn) {

        return userService.resetPassword(msisdn);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

        return userService.createUser(userDto);
    }

    @PutMapping
    public ResponseEntity<UserDto> put(@RequestBody UserDto userDto) {
        return userService.put(userDto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> get(@PathVariable("userId") UUID userId) {

        return userService.getUserById(userId);
    }

    @GetMapping("/record-product-order/{userId}")
    public ResponseEntity<UserDto> recordProductOrder(@PathVariable("userId") UUID userId, @RequestParam("productId") UUID productId) {


        try {
            Order order = new Order();
            order.setUserId(userId.toString());
            order.setProductId(productId.toString());
            orderRepository.save(order);
        } catch (Exception ignore) {
        }
        return userService.getUserById(userId);
    }

    @GetMapping("/service-providers/{userId}")
    public ResponseEntity<List<UserDto>> getServiceProviders(@PathVariable("userId") UUID userId) {

        return userService.getServiceProviders(userId);
    }

    @PostMapping("/products/rate/{userId}")
    public ResponseEntity<String> rateProducts(@PathVariable("userId") UUID userId, @RequestBody Map<UUID, Boolean> ratings) {
        return userService.rateProducts(userId, ratings);
    }
}
