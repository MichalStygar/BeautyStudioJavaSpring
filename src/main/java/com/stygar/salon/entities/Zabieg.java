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
    private Integer id;

   
    
    @Column(nullable=false,length=30)
    private String nazwa;
    
    @Column(nullable=false,length=4)
    private String cena;
    
     @OneToMany(mappedBy = "zabieg") 
    private Set<GabinetZabieg> gabinetzabieg;
    
    protected Zabieg(){}
    
    public Zabieg(String nazwa,String cena){
        this.nazwa = nazwa;
        this.cena = cena;
        
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

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }
    
    public Set<GabinetZabieg> getGabinetZabieg() {
        return gabinetzabieg;
    }

    public void setGabinetZabieg(Set<GabinetZabieg> gabinetzabieg) {
        this.gabinetzabieg = gabinetzabieg;
    }
    
   
    
}

