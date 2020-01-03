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
 //@Check(constraints = "imie in('^[A-Za-z]*$')")
public class Klient {
    @Id
    @Column(name="ID_klienta")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

   
    
    @Column(nullable=false,length=30)
  // @Pattern(regexp = "^[A-Za-z]*$") 
    private String imie;
    
    @Column(nullable=false,length=30)
    private String nazwisko;
    
    @Column(nullable=false,length=9)
    private String telefon;
    
    @ManyToOne 
    @JoinColumn(name="id_konta", nullable=false)
    private Konto konto;
    
    @OneToMany(mappedBy = "klient") 
    private Set<Rezerwacja> rezerwacja;
    
    
    protected Klient() {}

    public Klient(String imie,String nazwisko,String telefon,Konto konto) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.telefon = telefon;
        this.konto = konto;
        
    }
    
     public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
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

