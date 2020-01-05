package com.stygar.salon.controllers;

import com.stygar.salon.entities.Konto;
import com.stygar.salon.repositories.KontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RejestracjaController {
    @Autowired
    KontoRepository kontoRepository;
    
    //DODAWANIE KONTA
    @RequestMapping("/rejestracja/add")
    public String addKonto(Model model)
    {       
        Konto konto = new Konto();                
        model.addAttribute("konto", konto);         
        return "/rejestracja/add";  
    }
    
    @RequestMapping(value = "/rejestracja/add", method = RequestMethod.POST)
    public String addKlient(Model model,Konto konto)
    {
        
        String login = konto.getLogin();
        String haslo = konto.getHaslo();
        String email = konto.getEmail();
       String encoded = new BCryptPasswordEncoder().encode(haslo);
     
        kontoRepository.save(new Konto(login,encoded,email));
        
        
        return "/login";
    }
}
