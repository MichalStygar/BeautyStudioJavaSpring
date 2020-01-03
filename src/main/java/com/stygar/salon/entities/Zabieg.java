package com.stygar.salon.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Zabieg {
    @Id
    @Column(name="ID_zabiegu")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

   
    
    @Column(nullable=false,length=50)
    private String nazwa;
    
    @Column(nullable=false,length=4)
    private double cena;
    
     @OneToMany(mappedBy = "zabieg") 
    private Set<GabinetZabieg> gabinetzabieg;
    
    public Zabieg(){}
    
    public Zabieg(String nazwa,double cena){
        this.nazwa = nazwa;
        this.cena = cena;
        
    }
    
    public Zabieg(Long id,String nazwa,double cena){
        this.id = id;
        this.nazwa = nazwa;
        this.cena = cena;
        
    }
    
    
    
     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
    
    public Set<GabinetZabieg> getGabinetZabieg() {
        return gabinetzabieg;
    }

    public void setGabinetZabieg(Set<GabinetZabieg> gabinetzabieg) {
        this.gabinetzabieg = gabinetzabieg;
    }
    
   
    
}

