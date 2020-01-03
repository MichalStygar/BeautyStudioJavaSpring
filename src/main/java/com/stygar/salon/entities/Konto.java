package com.stygar.salon.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Konto {
    @Id
    @Column(name="ID_konta")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(nullable=false,length=30)
    private String login;
    
    @Column(nullable=false,length=30)
    private String haslo;
    
    @Column(nullable=false,length=30)
    private String email;
    
    @Column(nullable=false,length=30)
    private String uprawnienia;
    
    @Column(nullable=false)
    private Boolean aktywne = true;
    
    @OneToMany(mappedBy = "konto") 
    private Set<Pracownik> pracownik;
    
    @OneToMany(mappedBy = "konto") 
    private Set<Klient> klient;
    
    
    protected Konto() {}

    public Konto(String login,String haslo,String email,String uprawnienia,Boolean aktywne) {
       this.login =login;
       this.haslo = haslo;
       this.email = email;
       this.uprawnienia = uprawnienia;
       this.aktywne = aktywne;
       
       
    }
    
     public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUprawnienia() {
        return uprawnienia;
    }

    public void setUprawnienia(String uprawnienia) {
        this.uprawnienia = uprawnienia;
    }

    public Boolean getAktywne() {
        return aktywne;
    }

    public void setAktywne(Boolean aktywne) {
        this.aktywne = aktywne;
    }
    
    public Set<Pracownik> getPracownik() {
        return pracownik;
    }

    public void setPracownik(Set<Pracownik> pracownik) {
        this.pracownik = pracownik;
    }
    
    public Set<Klient> getKlient() {
        return klient;
    }

    public void setKlient(Set<Klient> klient) {
        this.klient = klient;
    }
    
}
