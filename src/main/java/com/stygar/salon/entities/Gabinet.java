/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stygar.salon.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Gabinet {

    @Id
    @Column(name="ID_gabinetu")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @Column(nullable=false,length=30)
    private  String nazwa;
    
    @OneToMany(mappedBy = "gabinet") 
    private Set<Wyposazenie> wyposazenie;
    
    @ManyToOne 
    @JoinColumn(name="id_pracownika", nullable = false)
    private Pracownik pracownik;
    
    @OneToMany(mappedBy = "gabinet") 
    private Set<GabinetZabieg> gabinetzabieg;


    protected Gabinet() {}

    public Gabinet(String nazwa,Pracownik pracownik ) {
        this.nazwa = nazwa;
        this.pracownik = pracownik;
       
    }

     public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    
    public Set<Wyposazenie> getWyposazenie() {
        return wyposazenie;
    }

    public void setWyposazenie(Set<Wyposazenie> wyposazenie) {
        this.wyposazenie = wyposazenie;
    }
    
    public Pracownik getPracownik() {
        return pracownik;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }
    
    public Set<GabinetZabieg> getGabinetZabieg() {
        return gabinetzabieg;
    }

    public void setGabinetZabieg(Set<GabinetZabieg> gabinetzabieg) {
        this.gabinetzabieg = gabinetzabieg;
    }

    

    

}
