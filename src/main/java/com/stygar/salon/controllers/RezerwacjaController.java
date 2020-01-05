package com.stygar.salon.controllers;

import com.stygar.salon.entities.GabinetZabieg;
import com.stygar.salon.entities.Klient;
import com.stygar.salon.entities.Konto;
import com.stygar.salon.entities.Pracownik;
import com.stygar.salon.entities.Rezerwacja;
import com.stygar.salon.repositories.GabinetZabiegRepository;
import com.stygar.salon.repositories.KlientRepository;
import com.stygar.salon.repositories.PracownikRepository;
import com.stygar.salon.repositories.RezerwacjaRepository;
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
public class RezerwacjaController {
    
    @Autowired
    RezerwacjaRepository rezerwacjaRepository;
    
    @Autowired
    KlientRepository klientRepository;
    
    @Autowired
    PracownikRepository pracownikRepository;
    
    @Autowired
    GabinetZabiegRepository gabinetZabiegRepository;
    
    //DODAWANIE REZERWACJI
    @RequestMapping("/rezerwacja/addrezerwacja")
    public String addRezerwacja(Model model)
    {       
        Rezerwacja rezerwacja = new Rezerwacja(); 
        ArrayList<Klient>  klient =  (ArrayList<Klient>) klientRepository.findAll();
        ArrayList<Pracownik>  pracownik =  (ArrayList<Pracownik>) pracownikRepository.findAll();
        ArrayList<GabinetZabieg>  gabinetZabieg =  (ArrayList<GabinetZabieg>) gabinetZabiegRepository.findAll();
        model.addAttribute("rezerwacja", rezerwacja);
        model.addAttribute("klientList", klient);
        model.addAttribute("pracownikList", pracownik);
        model.addAttribute("gabinetZabiegList", gabinetZabieg);
        return "/rezerwacja/addrezerwacja";  
    }
    
    @RequestMapping(value = "/rezerwacja/addrezerwacja", method = RequestMethod.POST)
    public String addRezerwacja(@ModelAttribute("rezerwacja")Rezerwacja rezerwacja,Klient klient,Pracownik pracownik,GabinetZabieg gabinetZabieg,Model model)
    {
        
        String data = rezerwacja.getDataGodzinaRezerwacji();
        Long idkl = klient.getId();
        Long idprac = pracownik.getId();
        Long idgab = gabinetZabieg.getId();
        
        Klient klie = klientRepository.findById(idkl).get();
        
        
        
       
        if(idprac==null)
        {
            
            GabinetZabieg gabzab = gabinetZabiegRepository.findById(idgab).get();
            rezerwacjaRepository.save(new Rezerwacja(klie,gabzab,data));
           
            
        }else{
            Pracownik prac = pracownikRepository.findById(idprac).get();
            rezerwacjaRepository.save(new Rezerwacja(klie,prac,data));
            
          
            
        }
        
        return "redirect:/rezerwacja/printallrezerwacja";
        
        
        
        
           
    }

    //WYSIWETLENIE WSZYSTKICH REZERWACJI
    @RequestMapping(value = "/rezerwacja/printallrezerwacja", method = RequestMethod.GET)
    public String printAllRezerwacja(Model model)
    {
        //List<Klient> klientList =  klientService.getAll();
        List<Rezerwacja> rezerwacjaList = rezerwacjaRepository.findAll();
        model.addAttribute("header","Lista wszystkich rezerwacji:"); 
        model.addAttribute("rezerwacjaList",rezerwacjaList); 
        
        return "/rezerwacja/printallrezerwacja";  

    }
    
     //USUWANIE REZERWACJI
    @GetMapping("/rezerwacja/printallrezerwacja/delete/{id}")
    public String deleteRezerwacja(@PathVariable("id")long id, Model model,Rezerwacja rezerwacja) {
     
        rezerwacjaRepository.deleteById(rezerwacja.getId());
        return "redirect:/rezerwacja/printallrezerwacja";
    }
    
      //EDYTOWANIE REZERWACJI
    @RequestMapping("/rezerwacja/printallrezerwacja/edycja/{id}")
    public String updateRezerwacja(@PathVariable Long id,Model model)
    {
        ArrayList<Klient>  klient =  (ArrayList<Klient>) klientRepository.findAll();          
        ArrayList<Pracownik>  pracownik =  (ArrayList<Pracownik>) pracownikRepository.findAll();
        ArrayList<GabinetZabieg>  gabinetZabieg =  (ArrayList<GabinetZabieg>) gabinetZabiegRepository.findAll();
        model.addAttribute("rezerwacja", rezerwacjaRepository.getOne(id));
        model.addAttribute("klientList", klient);
        model.addAttribute("pracownikList", pracownik);
        model.addAttribute("gabinetZabiegList", gabinetZabieg);
        return "/rezerwacja/editrezerwacja";
    }
    
    @RequestMapping(value = "/rezerwacja/printallrezerwacja/edycja/{id}", method = RequestMethod.POST)
    public String updateKonto(@PathVariable("id")Long id,@ModelAttribute(name = "rezerwacja")Rezerwacja rezerwacja,Model model){
  
        String data = rezerwacja.getDataGodzinaRezerwacji();
        
        Klient klie = rezerwacja.getKlient();
        Pracownik prac = rezerwacja.getPracownik();
        GabinetZabieg gabzab = rezerwacja.getGabinetZabieg();
        
        
        
       
        if(prac==null)
        {
            
            
            rezerwacjaRepository.save(new Rezerwacja(id,klie,gabzab,data));
           
            
        }else{
            
            rezerwacjaRepository.save(new Rezerwacja(id,klie,prac,data));
            
          
            
        }
       
        return "redirect:/rezerwacja/printallrezerwacja";
        
    }
    
    
}
