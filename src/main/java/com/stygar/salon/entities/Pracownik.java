package com.stygar.salon.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Pracownik {
    @Id
    @Column(name="ID_pracownika")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    
    
    @Column(nullable=false,length=30)
    private  String imie;
    
    @Column(nullable=false,length=30)
    private  String nazwisko;
    
    @Column(nullable=false,length=30)
    private  String stanowisko;
    
    @Column(nullable=false,length=30)
    private  String adres;
    
    @Column(nullable=false,length=30)
    private  LocalDate dataZatrudnienia;
    
    
   
    
    @OneToMany(mappedBy = "pracownik") 
    private Set<Gabinet> gabinet;
    
    @ManyToOne 
    @JoinColumn(name="id_konta", nullable=false)
    private Konto konto;
    
    @OneToMany(mappedBy = "pracownik") 
    private Set<Rezerwacja> rezerwacja;
    
   
    
    public Pracownik() {}

    public Pracownik(String imie,String nazwisko,String stanowisko,String adres,String dataZatrudnienia,Konto konto) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.stanowisko = stanowisko;
        this.adres = adres;
        if(dataZatrudnienia == ""){ this.dataZatrudnienia = null;}
        else{
            this.dataZatrudnienia = LocalDate.parse(dataZatrudnienia,DateTimeFormatter.ISO_LOCAL_DATE);
        }
        this.konto = konto;
            
    }
    
     
     
     public Pracownik(Long id,String imie,String nazwisko,String stanowisko,String adres,String dataZatrudnienia,Konto konto) {
        this.id = id;
         this.imie = imie;
        this.nazwisko = nazwisko;
        this.stanowisko = stanowisko;
        this.adres = adres;
        if(dataZatrudnienia == ""){ this.dataZatrudnienia = null;}
        else{
            this.dataZatrudnienia = LocalDate.parse(dataZatrudnienia,DateTimeFormatter.ISO_LOCAL_DATE);
        }
        this.konto = konto;
            
    }
    
   
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getDataZatrudnienia() {       
        if(dataZatrudnienia !=null){
            return dataZatrudnienia.toString();
        }else{
            return "";
        }
    }

    public void setDataZatrudnienia(String dataZatrudnienia) {
        LocalDate dataTime = LocalDate.parse(dataZatrudnienia,DateTimeFormatter.ISO_LOCAL_DATE);
        this.dataZatrudnienia = dataTime;
    }

  
    public Set<Gabinet> getGabinet() {
        return gabinet;
    }

    public void setGabinet(Set<Gabinet> gabinet) {
        this.gabinet = gabinet;
    }
    
    public Konto getKonto() {
        return konto;
    }

    public void setKonto(Konto konto) {
        this.konto = konto;
    }
    
    public Set<Rezerwacja> getRezerwacja() {
        return rezerwacja;
    }

    public void setRezerwacja(Set<Rezerwacja> rezerwacja) {
        this.rezerwacja = rezerwacja;
    }
    
  
    
    
    
    
    
    
    
    
}
