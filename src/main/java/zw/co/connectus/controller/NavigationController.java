package zw.co.connectus.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import zw.co.connectus.service.model.Marker;

import java.util.ArrayList;
import java.util.List;

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
        modelAndView.addObject("currentLng", currentLng);
        modelAndView.addObject("destinationLat", destinationLat);
        modelAndView.addObject("destinationLng", destinationLng);
        return modelAndView;
    }

    @GetMapping("/recommendations")
    public ModelAndView map(@RequestParam("currentLat") double currentLat,
                            @RequestParam("currentLng") double currentLng,
                            @RequestParam("markers") String markers) {

        List<Marker> markerList = new Gson().fromJson(markers, ArrayList.class);
        ModelAndView modelAndView = new ModelAndView("recommendationsMap");
        modelAndView.addObject("currentLat", currentLat);
        modelAndView.addObject("currentLng", currentLng);
        modelAndView.addObject("markerList", markerList);
        return modelAndView;
    }

}
