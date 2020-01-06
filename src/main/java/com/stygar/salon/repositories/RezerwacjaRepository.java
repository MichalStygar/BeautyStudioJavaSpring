package com.stygar.salon.repositories;

import com.stygar.salon.entities.Klient;
import com.stygar.salon.entities.Rezerwacja;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RezerwacjaRepository extends JpaRepository<Rezerwacja, Long>{

    public List<Rezerwacja> findByKlient(Klient kli);
    
}
