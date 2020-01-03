package com.stygar.salon.repositories;

import com.stygar.salon.entities.Klient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface KlientRepository extends JpaRepository<Klient, Long>{
    
}
