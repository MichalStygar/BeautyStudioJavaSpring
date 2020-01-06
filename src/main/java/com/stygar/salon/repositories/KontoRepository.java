package com.stygar.salon.repositories;

import com.stygar.salon.entities.Konto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface KontoRepository extends JpaRepository<Konto, Long>{

    public Konto getByLogin(String login);
    
}
