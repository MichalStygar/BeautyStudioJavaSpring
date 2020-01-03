package com.stygar.salon.controllers;

import com.stygar.salon.entities.Gabinet;
import com.stygar.salon.entities.Klient;
import com.stygar.salon.entities.Konto;
import com.stygar.salon.entities.Pracownik;
import com.stygar.salon.repositories.GabinetRepository;
import com.stygar.salon.repositories.PracownikRepository;
import java.util.ArrayList;
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
public class GabinetController {
    
    @Autowired
    GabinetRepository gabinetRepository;
    
    @Autowired
    PracownikRepository pracownikRepository;
    
    //DODAWANIE GABINETU
    @RequestMapping("/gabinet/addgabinet")
    public String addGabinet(Model model)
    {       
        Gabinet gabinet = new Gabinet(); 
        ArrayList<Pracownik>  pracownik =  (ArrayList<Pracownik>) pracownikRepository.findAll();
        model.addAttribute("gabinet", gabinet);
        model.addAttribute("pracownikList", pracownik);
        return "/gabinet/addgabinet";  
    }
    
    @RequestMapping(value = "/gabinet/addgabinet", method = RequestMethod.POST)
    public String addGabinet(@ModelAttribute("gabinet")Gabinet gabinet,Pracownik pracownik,Model model)
    {
        
        String nazwa = gabinet.getNazwa();        
        Long id = pracownik.getId();
        
        Pracownik prac = pracownikRepository.findById(id).get();
       
     
        gabinetRepository.save(new Gabinet(nazwa,prac));
        
        
        return "redirect:/gabinet/printallgabinet";
        
           
    }

    //WYSIWETLENIE WSZYSTKICH GABINETOW
    @RequestMapping(value = "/gabinet/printallgabinet", method = RequestMethod.GET)
    public String printAllGabinet(Model model)
    {
       
        List<Gabinet> gabinetList = gabinetRepository.findAll();
        model.addAttribute("header","Lista wszystkich gabinetów:"); 
        model.addAttribute("gabinetList",gabinetList); 
        
        return "/gabinet/printallgabinet";  

    }
    
     //USUWANIE KLIENTA
    @GetMapping("/gabinet/printallgabinet/delete/{id}")
    public String deleteGabinet(@PathVariable("id")long id, Model model,Gabinet gabinet) {
     
        gabinetRepository.deleteById(gabinet.getId());
        return "redirect:/gabinet/printallgabinet";
    }
    
      //EDYTOWANIE GABINETU
    @RequestMapping("/gabinet/printallgabinet/edycja/{id}")
    public String updateGabinet(@PathVariable Long id,Model model)
    {
        ArrayList<Pracownik>  pracownik =  (ArrayList<Pracownik>) pracownikRepository.findAll();          
        model.addAttribute("gabinet", gabinetRepository.getOne(id));
        model.addAttribute("pracownikList", pracownik);
        return "/gabinet/editgabinet";
    }
    
    @RequestMapping(value = "/gabinet/printallgabinet/edycja/{id}", method = RequestMethod.POST)
    public String updateGabinet(@PathVariable("id")Long id,@ModelAttribute(name = "gabinet")Gabinet gabinet,Model model){
  
        String nazwa = gabinet.getNazwa();        
        Pracownik prac =gabinet.getPracownik();
        
        
       
     
        gabinetRepository.save(new Gabinet(id,nazwa,prac));
       
        return "redirect:/gabinet/printallgabinet";
        
    }
    
}
