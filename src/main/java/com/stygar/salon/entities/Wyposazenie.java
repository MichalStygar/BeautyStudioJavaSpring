
package com.stygar.salon.entities;

import java.time.LocalDate;
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
    private Integer id;

    
    @Column(nullable=false,length=30)
    private  String nazwa;
    
    @Column(nullable=false,length=7)
    private double cena;
    
    @Column(nullable=false,length=10)
    private LocalDate data_zakupu;
    
    
    @ManyToOne 
    @JoinColumn(name="id_gabinetu", nullable=false)
    private Gabinet gabinet;

    
    
    
     protected Wyposazenie() {}

    public Wyposazenie(String nazwa,double cena,LocalDate data_zakupu,Gabinet gabinet) {
       this.nazwa = nazwa;
       this.cena = cena;
       this.data_zakupu = data_zakupu;
       this.gabinet = gabinet;
       
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

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public LocalDate getData_zakupu() {
        return data_zakupu;
    }

    public void setData_zakupu(LocalDate data_zakupu) {
        this.data_zakupu = data_zakupu;
    }
    
    public Gabinet getGabinet() {
        return gabinet;
    }

    public void setGabinet(Gabinet gabinet) {
        this.gabinet = gabinet;
    }
   
    
    
}
