
package com.stygar.salon.controllers;

import com.stygar.salon.entities.Konto;
import com.stygar.salon.repositories.KontoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class KontoController {
    
    @Autowired
    KontoRepository kontoRepository;
    
    //DODAWANIE KONTA
    @RequestMapping("/konto/addkonto")
    public String addKonto(Model model)
    {       
        Konto konto = new Konto();                
        model.addAttribute("konto", konto);         
        return "/konto/addkonto";  
    }
    
    @RequestMapping(value = "/konto/addkonto", method = RequestMethod.POST)
    public String addKonto(Model model,Konto konto)
    {
        
        String login = konto.getLogin();
        String haslo = konto.getHaslo();
        String email = konto.getEmail();
       String encoded = new BCryptPasswordEncoder().encode(haslo);
     
        kontoRepository.save(new Konto(login,encoded,email));
        
        
        return "redirect:/konto/printallkonto";
        
           
    }

    //WYSIWETLENIE WSZYSTKICH KONT
    @RequestMapping(value = "/konto/printallkonto", method = RequestMethod.GET)
    public String printAllKonto(Model model)
    {
        //List<Klient> klientList =  klientService.getAll();
        List<Konto> kontoList = kontoRepository.findAll();
        model.addAttribute("header","Lista wszystkich kont:"); 
        model.addAttribute("kontoList",kontoList); 
        
        return "/konto/printallkonto";  

    }
    
     //USUWANIE KONTA
    @GetMapping("/konto/printallkonto/delete/{id}")
    public String deleteKonto(@PathVariable("id")long id, Model model,Konto konto) {
     
    kontoRepository.deleteById(konto.getId());
    return "redirect:/konto/printallkonto";
    }
    
     //EDYTOWANIE KONTA
    @RequestMapping("/konto/printallkonto/edycja/{id}")
    public String updateKonto(@PathVariable Long id,Model model)
    {
                  
        model.addAttribute("konto", kontoRepository.getOne(id));
        
        return "/konto/editkonto";
    }
    
    @RequestMapping(value = "/konto/printallkonto/edycja/{id}", method = RequestMethod.POST)
    public String updateKonto(@PathVariable("id")Long id,@ModelAttribute(name = "konto")Konto konto,Model model){
  
        String login = konto.getLogin();
        String haslo = konto.getHaslo();
        String email = konto.getEmail();
        
        
     
        kontoRepository.save(new Konto(id,login,haslo,email));
       
        return "redirect:/konto/printallkonto";
        
    }
    
    
}
