package com.dima.niceweb.globe;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GlobeController {

    @GetMapping("/globeindex")
    public String globeindex() {

        return "Globe/globeindex";
    }
    
    @GetMapping("/showReg")
    public String showReg() {

        return "Globe/showReg";
    }

}