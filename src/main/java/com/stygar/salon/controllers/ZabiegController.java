package com.stygar.salon.controllers;


import com.stygar.salon.entities.Klient;
import com.stygar.salon.entities.Zabieg;
import com.stygar.salon.repositories.ZabiegRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ZabiegController {
    @Autowired
    ZabiegRepository zabiegRepository;
    
    //DODAWANIE ZABIEGU
    @RequestMapping("/zabieg/addzabieg")
    public String addZabieg(Model model)
    {       
        Zabieg zabieg = new Zabieg(); 
        
        model.addAttribute("zabieg", zabieg);
       
        return "/zabieg/addzabieg";  
    }
    
    @RequestMapping(value = "/zabieg/addzabieg", method = RequestMethod.POST)
    public String addZabieg(@ModelAttribute("zabieg")Zabieg zabieg,Model model)
    {
        
        String nazwa = zabieg.getNazwa();
        double cena = zabieg.getCena();
        
        
        zabiegRepository.save(new Zabieg(nazwa,cena));
        
        
        return "redirect:/zabieg/printallzabieg";
        
           
    }

    //WYSIWETLENIE WSZYSTKICH ZABIEGOW
    @RequestMapping(value = "/zabieg/printallzabieg", method = RequestMethod.GET)
    public String printAllZabieg(Model model)
    {
        
        List<Zabieg> zabiegList = zabiegRepository.findAll();
        model.addAttribute("header","Lista wszystkich zabiegów:"); 
        model.addAttribute("zabiegList",zabiegList); 
        
        return "/zabieg/printallzabieg";  

    }
    
    //USUWANIE ZABIEGU
    @GetMapping("/zabieg/printallzabieg/delete/{id}")
    public String deleteZabieg(@PathVariable("id")long id, Model model,Zabieg zabieg) {
     
        zabiegRepository.deleteById(zabieg.getId());
        return "redirect:/zabieg/printallzabieg";
    }
    
      //EDYTOWANIE ZABIEGU
    @RequestMapping("/zabieg/printallzabieg/edycja/{id}")
    public String updateZabieg(@PathVariable Long id,Model model)
    {
                  
        model.addAttribute("zabieg", zabiegRepository.getOne(id));
        
        return "/zabieg/editzabieg";
    }
    
    @RequestMapping(value = "/zabieg/printallzabieg/edycja/{id}", method = RequestMethod.POST)
    public String updateZabieg(@PathVariable("id")Long id,@ModelAttribute(name = "zabieg")Zabieg zabieg,Model model){
  
        String nazwa = zabieg.getNazwa();
        double cena = zabieg.getCena();
        
        
        zabiegRepository.save(new Zabieg(id,nazwa,cena));
       
        return "redirect:/zabieg/printallzabieg";
        
    }
    
    
}
