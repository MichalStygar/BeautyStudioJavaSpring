
package com.stygar.salon.controllers;

import com.stygar.salon.entities.Gabinet;
import com.stygar.salon.entities.Klient;
import com.stygar.salon.entities.Konto;
import com.stygar.salon.entities.Wyposazenie;
import com.stygar.salon.repositories.GabinetRepository;
import com.stygar.salon.repositories.WyposazenieRepository;
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
public class WyposazenieController {
    
    @Autowired
    WyposazenieRepository wyposazenieRepository;
    
    @Autowired
    GabinetRepository gabinetRepository;
    
    
    //DODAWANIE WYPOSAZENIA
    @RequestMapping("/wyposazenie/addwyposazenie")
    public String addWyposazenie(Model model)
    {       
        Wyposazenie wyposazenie = new Wyposazenie(); 
        ArrayList<Gabinet>  gabinet =  (ArrayList<Gabinet>) gabinetRepository.findAll();
        model.addAttribute("wyposazenie", wyposazenie);
        model.addAttribute("gabinetList", gabinet);
        return "/wyposazenie/addwyposazenie";  
    }
    
    @RequestMapping(value = "/wyposazenie/addwyposazenie", method = RequestMethod.POST)
    public String addWyposazenie(@ModelAttribute("wyposazenie")Wyposazenie wyposazenie,Gabinet gabinet,Model model)
    {
        
        String nazwa = wyposazenie.getNazwa();
        double cena = wyposazenie.getCena();
        String data = wyposazenie.getDataZakupu();
        Long id = gabinet.getId();
        
        Gabinet gab = gabinetRepository.findById(id).get();
       
     
        wyposazenieRepository.save(new Wyposazenie(nazwa,cena,data,gab));
        
        
        return "redirect:/wyposazenie/printallwyposazenie";
        
           
    }

    //WYSIWETLENIE WYPOSAZEN
    @RequestMapping(value = "/wyposazenie/printallwyposazenie", method = RequestMethod.GET)
    public String printAllWyposazenie(Model model)
    {
        //List<Klient> klientList =  klientService.getAll();
        List<Wyposazenie> wyposazenieList = wyposazenieRepository.findAll();
        model.addAttribute("header","Lista wszystkich wyposażeń:"); 
        model.addAttribute("wyposazenieList",wyposazenieList); 
        
        return "/wyposazenie/printallwyposazenie";  

    }
    
    //USUWANIE KLIENTA
    @GetMapping("/wyposazenie/printallwyposazenie/delete/{id}")
    public String deleteWyposazenie(@PathVariable("id")long id, Model model,Wyposazenie wyposazenie) {
     
        wyposazenieRepository.deleteById(wyposazenie.getId());
        return "redirect:/wyposazenie/printallwyposazenie";
    }
    
      //EDYTOWANIE KLIENTA
    @RequestMapping("/wyposazenie/printallwyposazenie/edycja/{id}")
    public String updateWyposazenie(@PathVariable Long id,Model model)
    {
        ArrayList<Gabinet>  gabinet =  (ArrayList<Gabinet>) gabinetRepository.findAll();          
        model.addAttribute("wyposazenie", wyposazenieRepository.getOne(id));
        model.addAttribute("gabinetList", gabinet);
        return "/wyposazenie/editwyposazenie";
    }
    
    @RequestMapping(value = "/wyposazenie/printallwyposazenie/edycja/{id}", method = RequestMethod.POST)
    public String updateWyposazenie(@PathVariable("id")Long id,@ModelAttribute(name = "wyposazenie")Wyposazenie wyposazenie,Model model){
  
        String nazwa = wyposazenie.getNazwa();
        double cena = wyposazenie.getCena();
        String data = wyposazenie.getDataZakupu();
        Gabinet gab = wyposazenie.getGabinet();
       
     
        wyposazenieRepository.save(new Wyposazenie(id,nazwa,cena,data,gab));
       
        return "redirect:/wyposazenie/printallwyposazenie";
        
    }
    
    
    
    
}
