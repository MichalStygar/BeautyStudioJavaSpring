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
    private Integer id;

   
    
    @Column(nullable=false,length=20)
    private  LocalDateTime dataGodzinaRezerwacji;

    @ManyToOne 
    @JoinColumn(name="id_klienta", nullable=false)
    private Klient klient;
    
    @ManyToOne 
    @JoinColumn(name="id_pracownika", nullable=true)
    private Pracownik pracownik;
    
    @ManyToOne 
    @JoinColumn(name="id_gabZab", nullable=false)
    private GabinetZabieg gabinetzabieg;

    
    
    protected Rezerwacja() {}

    public Rezerwacja(Klient klient,Pracownik pracownik,GabinetZabieg gabinetzabieg,String dataGodzinaRezerwacji ) {
        this.klient = klient;
        this.pracownik = pracownik;
        this.gabinetzabieg = gabinetzabieg;
        if(dataGodzinaRezerwacji == ""){ this.dataGodzinaRezerwacji = null;}
        else{
            this.dataGodzinaRezerwacji = LocalDateTime.parse(dataGodzinaRezerwacji,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
        
    }
    
    public Rezerwacja(Klient klient,GabinetZabieg gabinetzabieg,String dataGodzinaRezerwacji ) {
        this.klient = klient;
        this.gabinetzabieg = gabinetzabieg;
        if(dataGodzinaRezerwacji == ""){ this.dataGodzinaRezerwacji = null;}
        else{
            this.dataGodzinaRezerwacji = LocalDateTime.parse(dataGodzinaRezerwacji,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
        
        
    }
    
     public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
