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
public class GabinetZabieg {
    @Id
    @Column(name="ID_zabGab")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    
    
    @ManyToOne
    @JoinColumn(name="id_zabiegu", nullable=false)
    private Zabieg zabieg;
    
    @ManyToOne 
    @JoinColumn(name="id_gabinetu", nullable=false)
    private Gabinet gabinet;
    
    @OneToMany(mappedBy = "gabinetzabieg") 
    private Set<Rezerwacja> rezerwacja;
    
    public GabinetZabieg() {}

    public GabinetZabieg(Zabieg zabieg,Gabinet gabinet) {
        this.zabieg = zabieg;
        this.gabinet = gabinet;
        
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Zabieg getZabieg() {
        return zabieg;
    }

    public void setZabieg(Zabieg zabieg) {
        this.zabieg = zabieg;
    }
    
    public Gabinet getGabinet() {
        return gabinet;
    }

    public void setGabinet(Gabinet gabinet) {
        this.gabinet = gabinet;
    }
    
    public Set<Rezerwacja> getRezerwacja() {
        return rezerwacja;
    }

    public void setRezerwacja(Set<Rezerwacja> rezerwacja) {
        this.rezerwacja = rezerwacja;
    }
}
