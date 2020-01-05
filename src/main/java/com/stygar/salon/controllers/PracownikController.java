
package com.stygar.salon.controllers;

import com.stygar.salon.entities.Klient;
import com.stygar.salon.entities.Konto;
import com.stygar.salon.entities.Pracownik;
import com.stygar.salon.repositories.KontoRepository;
import com.stygar.salon.repositories.PracownikRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PracownikController {
    
    
    @Autowired
    PracownikRepository pracownikRepository;
    
    @Autowired
    KontoRepository kontoRepository;
    
    //DODAWANIE KLIENTA
    @RequestMapping("/pracownik/addpracownik")
    public String addPracownik(Model model)
    {       
        Pracownik pracownik = new Pracownik(); 
        ArrayList<Konto>  konto =  (ArrayList<Konto>) kontoRepository.findAll();
        ArrayList<Pracownik>  pracowniklist =  (ArrayList<Pracownik>) pracownikRepository.findAll();
        model.addAttribute("pracownik", pracownik);
        model.addAttribute("kontoList", konto);
        model.addAttribute("pracownikList", pracowniklist);
        return "/pracownik/addpracownik";  
    }
    
    @RequestMapping(value = "/pracownik/addpracownik", method = RequestMethod.POST)
    public String addPracownik(@ModelAttribute("pracownik")Pracownik pracownik,Konto konto,Model model)
    {
        
        String imie = pracownik.getImie();
        String nazwisko = pracownik.getNazwisko();
        String adres = pracownik.getAdres();
        String stanowisko = pracownik.getStanowisko();
        String data = pracownik.getDataZatrudnienia();
        Long id = konto.getId();
        Long idp = pracownik.getId();
        Konto kont = kontoRepository.findById(id).get();
       
        if(idp==null){                                  
            pracownikRepository.save(new Pracownik(imie,nazwisko,adres,stanowisko,data,kont));
            
        }else{
            Pracownik szef = pracownikRepository.findById(idp).get();                  
            pracownikRepository.save(new Pracownik(imie,nazwisko,adres,stanowisko,data,kont,szef));
            
        }
        
        
        
        return "redirect:/pracownik/printallpracownik";
        
           
    }

    //WYSIWETLENIE WSZYSTKICH KLIENTOW
    @RequestMapping(value = "/pracownik/printallpracownik", method = RequestMethod.GET)
    public String printAllPracownik(Model model)
    {
        //List<Klient> klientList =  klientService.getAll();
        List<Pracownik> pracownikList = pracownikRepository.findAll();
        model.addAttribute("header","Lista wszystkich pracowników:"); 
        model.addAttribute("pracownikList",pracownikList); 
        
        return "/pracownik/printallpracownik";  

    }
    //USUWANIE PRACOWNIKA
    @GetMapping("/pracownik/printallpracownik/delete/{id}")
    public String deletePracownik(@PathVariable("id")long id, Model model,Klient klient) {
     
        pracownikRepository.deleteById(klient.getId());
        return "redirect:/pracownik/printallpracownik";
    }
    
      //EDYTOWANIE PRACOWNIKA
    @RequestMapping("/pracownik/printallpracownik/edycja/{id}")
    public String updatePracownik(@PathVariable Long id,Model model)
    {
        ArrayList<Konto>  konto =  (ArrayList<Konto>) kontoRepository.findAll();
        ArrayList<Pracownik>  pracownik =  (ArrayList<Pracownik>) pracownikRepository.findAll();
        model.addAttribute("pracownik", pracownikRepository.getOne(id));
        model.addAttribute("kontoList", konto);
        model.addAttribute("pracownikList", pracownik);
        return "/pracownik/editpracownik";
    }
    
    @RequestMapping(value = "/pracownik/printallpracownik/edycja/{id}", method = RequestMethod.POST)
    public String updateKonto(@PathVariable("id")Long id,@ModelAttribute(name = "pracownik")Pracownik pracownik,Model model){
  
        
        String imie = pracownik.getImie();
        String nazwisko = pracownik.getNazwisko();
        String adres = pracownik.getAdres();
        String stanowisko = pracownik.getStanowisko();
        String data = pracownik.getDataZatrudnienia();
        Konto konto =pracownik.getKonto();

        Pracownik idp = pracownik.getSzef();
        
       
        if(idp==null){
    
            pracownikRepository.save(new Pracownik(id,imie,nazwisko,adres,stanowisko,data,konto));
            
        }else{

            pracownikRepository.save(new Pracownik(id,imie,nazwisko,adres,stanowisko,data,konto,idp));
            
        }
       
        return "redirect:/pracownik/printallpracownik";
        
    }
    
}
