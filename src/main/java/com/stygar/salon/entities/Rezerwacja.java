package com.stygar.salon.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Rezerwacja {
    @Id
    @Column(name="ID_rezerwacji")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

   
    
    @Column(nullable=false,length=20)
    private  LocalDateTime dataGodzinaRezerwacji;

    @ManyToOne 
    @JoinColumn(name="id_klienta", nullable=false)
    private Klient klient;
    
    @ManyToOne 
    @JoinColumn(name="id_pracownika", nullable=true)
    private Pracownik pracownik;
    
    @ManyToOne 
    @JoinColumn(name="id_gabinetZabieg", nullable=true)
    private GabinetZabieg gabinetzabieg;

    
    
    public Rezerwacja() {}

    public Rezerwacja(Klient klient,GabinetZabieg gabinetzabieg,String dataGodzinaRezerwacji ) {
        this.klient = klient;      
        this.gabinetzabieg = gabinetzabieg;
        if(dataGodzinaRezerwacji == ""){ this.dataGodzinaRezerwacji = null;}
        else{
            this.dataGodzinaRezerwacji = LocalDateTime.parse(dataGodzinaRezerwacji,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
        
    }
    
    public Rezerwacja(Klient klient,Pracownik pracownik,String dataGodzinaRezerwacji ) {
        this.klient = klient;
        this.pracownik = pracownik;
        if(dataGodzinaRezerwacji == ""){ this.dataGodzinaRezerwacji = null;}
        else{
            this.dataGodzinaRezerwacji = LocalDateTime.parse(dataGodzinaRezerwacji,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
        
        
    }
    
    
    public Rezerwacja(Long id,Klient klient,GabinetZabieg gabinetzabieg,String dataGodzinaRezerwacji ) {
        this.id = id;
        this.klient = klient;      
        this.gabinetzabieg = gabinetzabieg;
        if(dataGodzinaRezerwacji == ""){ this.dataGodzinaRezerwacji = null;}
        else{
            this.dataGodzinaRezerwacji = LocalDateTime.parse(dataGodzinaRezerwacji,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
        
    }
    
    public Rezerwacja(Long id,Klient klient,Pracownik pracownik,String dataGodzinaRezerwacji ) {
        this.id = id;
        this.klient = klient;
        this.pracownik = pracownik;
        if(dataGodzinaRezerwacji == ""){ this.dataGodzinaRezerwacji = null;}
        else{
            this.dataGodzinaRezerwacji = LocalDateTime.parse(dataGodzinaRezerwacji,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
        
        
    }
    
     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataGodzinaRezerwacji() {      
        if(dataGodzinaRezerwacji !=null){
            return dataGodzinaRezerwacji.toString();
        }else{
            return "";
        }
    }
          

    public void setDataGodzinaRezerwacji(String dataGodzinaRezerwacji) {       
        LocalDateTime dataTime = LocalDateTime.parse(dataGodzinaRezerwacji,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.dataGodzinaRezerwacji = dataTime;
    }
    
    
    
    
    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }
    
    public Pracownik getPracownik() {
        return pracownik;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }
    
    public GabinetZabieg getGabinetZabieg() {
        return gabinetzabieg;
    }

    public void setGabinetZabieg(GabinetZabieg gabinetzabieg) {
        this.gabinetzabieg = gabinetzabieg;
    }
    
}
