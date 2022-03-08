package zw.co.connectus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zw.co.connectus.dal.entity.User;
import zw.co.connectus.service.UserServiceImpl;
import zw.co.connectus.service.model.CheckDto;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@GetMapping(path = "/api/v1/user/check/{msisdn}")
	public CheckDto check(@PathVariable("msisdn") String msisdn) {

		return userService.check(msisdn);
	}

}
