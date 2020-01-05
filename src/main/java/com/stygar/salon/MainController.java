package com.stygar.salon;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    
    
    @RequestMapping("/login")
    public String admin() {        
        return "login"; 
    }
    
    @RequestMapping("/home/salon")
    public String home() {        
        return "/home/salon"; 
    }
    
    
    
    
    
    

    //Obsluga bledow
    @ExceptionHandler
    public String handlerException(Model model, Exception exception)
    {
        String message = exception.getMessage();
        model.addAttribute("message", message);
        return "error";
    }
}
