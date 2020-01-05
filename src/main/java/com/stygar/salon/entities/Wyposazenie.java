
package com.stygar.salon.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Wyposazenie {
    @Id
    @Column(name="ID_wyposazenia")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    
    @Column(nullable=false,length=30)
    private  String nazwa;
    
    @Column(nullable=false,length=7)
    private double cena;
    
    @Column(nullable=false,length=10)
    private LocalDate dataZakupu;
    
    
    @ManyToOne 
    @JoinColumn(name="id_gabinetu", nullable=false)
    private Gabinet gabinet;

    
    
    
     public Wyposazenie() {}

    public Wyposazenie(String nazwa,double cena,String dataZakupu,Gabinet gabinet) {
       this.nazwa = nazwa;
       this.cena = cena;
       if(dataZakupu == ""){ this.dataZakupu = null;}
        else{
            this.dataZakupu = LocalDate.parse(dataZakupu,DateTimeFormatter.ISO_LOCAL_DATE);
        }
       this.gabinet = gabinet;
       
    }
    
    public Wyposazenie(Long id,String nazwa,double cena,String dataZakupu,Gabinet gabinet) {
       this.id = id;
        this.nazwa = nazwa;
       this.cena = cena;
       if(dataZakupu == ""){ this.dataZakupu = null;}
        else{
            this.dataZakupu = LocalDate.parse(dataZakupu,DateTimeFormatter.ISO_LOCAL_DATE);
        }
       this.gabinet = gabinet;
       
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

    public String getDataZakupu() {
        if(dataZakupu !=null){
            return dataZakupu.toString();
        }else{
            return "";
        }
    }

    public void setDataZakupu(String dataZakupu) {
        LocalDate dataTime = LocalDate.parse(dataZakupu,DateTimeFormatter.ISO_LOCAL_DATE);
        this.dataZakupu = dataTime;
    }
    
    public Gabinet getGabinet() {
        return gabinet;
    }

    public void setGabinet(Gabinet gabinet) {
        this.gabinet = gabinet;
    }
   
    
    
}
