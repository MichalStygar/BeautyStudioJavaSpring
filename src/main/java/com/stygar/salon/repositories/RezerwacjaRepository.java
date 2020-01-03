package com.stygar.salon.repositories;

import com.stygar.salon.entities.Rezerwacja;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RezerwacjaRepository extends JpaRepository<Rezerwacja, Long>{
    
}
