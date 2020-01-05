package com.stygar.salon.controllers;

import com.stygar.salon.entities.Gabinet;
import com.stygar.salon.entities.GabinetZabieg;
import com.stygar.salon.entities.Klient;
import com.stygar.salon.entities.Konto;
import com.stygar.salon.entities.Zabieg;
import com.stygar.salon.repositories.GabinetRepository;
import com.stygar.salon.repositories.GabinetZabiegRepository;
import com.stygar.salon.repositories.ZabiegRepository;
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
public class GabinetZabiegController {
    
    @Autowired
    GabinetZabiegRepository gabinetZabiegRepository;
    
    @Autowired
    GabinetRepository gabinetRepository;
    
    @Autowired
    ZabiegRepository zabiegRepository;
    
     //DODAWANIE GABINETZABIEG
    @RequestMapping("/GabinetZabieg/addGabinetZabieg")
    public String addGabinetZabieg(Model model)
    {       
        GabinetZabieg gabinetZabieg = new GabinetZabieg(); 
        ArrayList<Gabinet>  gabinet =  (ArrayList<Gabinet>) gabinetRepository.findAll();
        ArrayList<Zabieg>  zabieg =  (ArrayList<Zabieg>) zabiegRepository.findAll();
        model.addAttribute("gabinetZabieg", gabinetZabieg);
        model.addAttribute("gabinetList", gabinet);
        model.addAttribute("zabiegList", zabieg);
        return "/GabinetZabieg/addGabinetZabieg";  
    }
    
    @RequestMapping(value = "/GabinetZabieg/addGabinetZabieg", method = RequestMethod.POST)
    public String addGabinetZabieg(@ModelAttribute("GabinetZabieg")GabinetZabieg gabinetZabieg,Gabinet gabinet,Zabieg zabieg, Model model)
    {
        
        
        Long id = gabinet.getId();
        Long idz = zabieg.getId();
        
        Gabinet gab = gabinetRepository.findById(id).get();
        Zabieg zab = zabiegRepository.findById(idz).get();
       
     
        gabinetZabiegRepository.save(new GabinetZabieg(zab,gab));
        
        
        return "redirect:/GabinetZabieg/printallGabinetZabieg";
        
           
    }

    //WYSIWETLENIE GABINETOWZABIEGOW
    @RequestMapping(value = "/GabinetZabieg/printallGabinetZabieg", method = RequestMethod.GET)
    public String printAllGabinetZabieg(Model model)
    {
        //List<Klient> klientList =  klientService.getAll();
        List<GabinetZabieg> gabinetZabiegList = gabinetZabiegRepository.findAll();
        model.addAttribute("header","Lista zabiegów wykonywanych w gabinetach:"); 
        model.addAttribute("gabinetZabiegList",gabinetZabiegList); 
        
        return "/GabinetZabieg/printallGabinetZabieg";  

    }
    
     //USUWANIE GABINETZABIEG
    @GetMapping("/GabinetZabieg/printallGabinetZabieg/delete/{id}")
    public String deleteKlient(@PathVariable("id")long id, Model model,GabinetZabieg gabinetZabieg) {
     
        gabinetZabiegRepository.deleteById(gabinetZabieg.getId());
        return "redirect:/GabinetZabieg/printallGabinetZabieg";
    }
    
      //EDYTOWANIE GABINETZABIEG
    @RequestMapping("/GabinetZabieg/printallGabinetZabieg/edycja/{id}")
    public String updateKonto(@PathVariable Long id,Model model)
    {
        ArrayList<Gabinet>  gabinet =  (ArrayList<Gabinet>) gabinetRepository.findAll();          
        ArrayList<Zabieg>  zabieg =  (ArrayList<Zabieg>) zabiegRepository.findAll();          
        model.addAttribute("gabinetZabieg", gabinetZabiegRepository.getOne(id));
        model.addAttribute("gabinetList", gabinet);
        model.addAttribute("zabiegList", zabieg);
        return "/GabinetZabieg/editGabinetZabieg";
    }
    
    @RequestMapping(value = "/GabinetZabieg/printallGabinetZabieg/edycja/{id}", method = RequestMethod.POST)
    public String updateKonto(@PathVariable("id")Long id,@ModelAttribute(name = "gabinetZabieg")GabinetZabieg gabinetZabieg,Model model){
  
        
        
        Gabinet gab = gabinetZabieg.getGabinet();
        Zabieg zab = gabinetZabieg.getZabieg();
       
     
        gabinetZabiegRepository.save(new GabinetZabieg(id,zab,gab));
       
        return "redirect:/GabinetZabieg/printallGabinetZabieg";
        
    }
    
}
