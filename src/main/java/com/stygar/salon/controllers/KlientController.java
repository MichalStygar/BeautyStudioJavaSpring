
package com.stygar.salon.controllers;

import com.stygar.salon.entities.Klient;
import com.stygar.salon.entities.Konto;
import com.stygar.salon.repositories.KlientRepository;
import com.stygar.salon.repositories.KontoRepository;
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
public class KlientController {
    
    @Autowired
    KlientRepository klientRepository;
    
    @Autowired
    KontoRepository kontoRepository;
    
    @RequestMapping("/")
    public String home(){
        
        return "/page";
    //   return "home";
    //return "/home/salon";
       
    }
    
    
    //DODAWANIE KLIENTA
    @RequestMapping("/klient/addklient")
    public String addKlient(Model model)
    {       
        Klient klient = new Klient(); 
        ArrayList<Konto>  konto =  (ArrayList<Konto>) kontoRepository.findAll();
        model.addAttribute("klient", klient);
        model.addAttribute("kontoList", konto);
        return "/klient/addklient";  
    }
    
    @RequestMapping(value = "/klient/addklient", method = RequestMethod.POST)
    public String addKlient(@ModelAttribute("klient")Klient klient,Konto konto,Model model)
    {
        
        String imie = klient.getImie();
        String nazwisko = klient.getNazwisko();
        String telefon = klient.getTelefon();
        Long id = konto.getId();
        
        Konto kont = kontoRepository.findById(id).get();
       
     
        klientRepository.save(new Klient(imie,nazwisko,telefon,kont));
        
        
        return "redirect:/klient/printallklient";
        
           
    }

    //WYSIWETLENIE WSZYSTKICH KLIENTOW
    @RequestMapping(value = "/klient/printallklient", method = RequestMethod.GET)
    public String printAllKlient(Model model)
    {
        //List<Klient> klientList =  klientService.getAll();
        List<Klient> klientList = klientRepository.findAll();
        model.addAttribute("header","Lista wszystkich klientow:"); 
        model.addAttribute("klientList",klientList); 
        
        return "/klient/printallklient";  

    }
    
    //USUWANIE KLIENTA
    @GetMapping("/klient/printallklient/delete/{id}")
    public String deleteKlient(@PathVariable("id")long id, Model model,Klient klient) {
     
        klientRepository.deleteById(klient.getId());
        return "redirect:/klient/printallklient";
    }
    
      //EDYTOWANIE KLIENTA
    @RequestMapping("/klient/printallklient/edycja/{id}")
    public String updateKonto(@PathVariable Long id,Model model)
    {
        ArrayList<Konto>  konto =  (ArrayList<Konto>) kontoRepository.findAll();          
        model.addAttribute("klient", klientRepository.getOne(id));
        model.addAttribute("kontoList", konto);
        return "/klient/editklient";
    }
    
    @RequestMapping(value = "/klient/printallklient/edycja/{id}", method = RequestMethod.POST)
    public String updateKonto(@PathVariable("id")Long id,@ModelAttribute(name = "klient")Klient klient,Model model){
  
        String imie = klient.getImie();
        String nazwisko = klient.getNazwisko();
        String telefon = klient.getTelefon();        
        Konto konto = klient.getKonto();
        
        klientRepository.save(new Klient(id,imie,nazwisko,telefon,konto));
       
        return "redirect:/klient/printallklient";
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
