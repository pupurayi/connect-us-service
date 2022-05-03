package zw.co.connectus.controller;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.connectus.dal.entity.User;
import zw.co.connectus.service.UserServiceImpl;
import zw.co.connectus.service.mapper.DtoMapper;
import zw.co.connectus.service.model.AuthResponseDto;
import zw.co.connectus.service.model.CheckDto;
import zw.co.connectus.service.model.SignInDto;
import zw.co.connectus.service.model.UserDto;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceImpl userService;

    @GetMapping(path = "/check/{msisdn}")
    public CheckDto check(@PathVariable("msisdn") String msisdn) {

        return userService.check(msisdn);
    }

    @PostMapping(path = "/sign-up")
    public AuthResponseDto signUp(@RequestBody UserDto userDto) {

        return userService.signUp(userDto);
    }

    @PostMapping(path = "/sign-in")
    public AuthResponseDto signIn(@RequestBody SignInDto signInDto) {

        return userService.signIn(signInDto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> get(@PathVariable("userId") UUID userId) {

        return userService.getUserById(userId);
    }

    @PutMapping
    public ResponseEntity<UserDto> put(@RequestBody UserDto userDto) {
        return userService.put(userDto);
    }
}
