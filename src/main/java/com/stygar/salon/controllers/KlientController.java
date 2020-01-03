
package com.stygar.salon.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KlientController {
    @RequestMapping("/")
    public String home(){
        
       // return "/page";
    //   return "home";
    return "/home/salon";
       
    }
}
