package zw.co.connectus.controller;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zw.co.connectus.service.UserServiceImpl;
import zw.co.connectus.service.model.AuthResponseDto;
import zw.co.connectus.service.model.CheckDto;
import zw.co.connectus.service.model.SignInDto;
import zw.co.connectus.service.model.UserDto;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserServiceImpl userService;

	@GetMapping(path = "/check/{msisdn}")
	public CheckDto check(@PathVariable("msisdn") String msisdn) {

		logger.info(msisdn);
		final CheckDto check = userService.check(msisdn);
		logger.info(new Gson().toJson(logger));
		return check;
	}

	@PostMapping(path = "/sign-up")
	public AuthResponseDto signUp(@RequestBody UserDto userDto) {

		return userService.signUp(userDto);
	}

	@PostMapping(path = "/sign-in")
	public AuthResponseDto signIn(@RequestBody SignInDto signInDto) {

		return userService.signIn(signInDto);
	}

}
