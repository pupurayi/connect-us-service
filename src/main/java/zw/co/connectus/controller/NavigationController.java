package zw.co.connectus.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/navigation")
@CrossOrigin(origins = "*")
public class NavigationController {

	@GetMapping
	public ModelAndView map(@RequestParam("currentLat") double currentLat,
	                        @RequestParam("currentLng") double currentLng,
	                        @RequestParam("destinationLat") double destinationLat,
	                        @RequestParam("destinationLng") double destinationLng
	) {
		ModelAndView modelAndView = new ModelAndView("navigationMap");
		modelAndView.addObject("currentLat", currentLat);
		modelAndView.addObject("currentLat", currentLat);
		modelAndView.addObject("currentLat", currentLat);
		modelAndView.addObject("currentLat", currentLat);
		return modelAndView;
	}

}
