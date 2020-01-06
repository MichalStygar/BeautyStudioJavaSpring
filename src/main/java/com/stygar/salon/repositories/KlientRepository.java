package com.stygar.salon.repositories;

import com.stygar.salon.entities.Klient;
import com.stygar.salon.entities.Konto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface KlientRepository extends JpaRepository<Klient, Long>{

    public Klient getByKonto(Konto id);

    

    public List<Klient> findByKonto(Konto id);

    public Klient getById(Konto idko);

    

   

    

    

    
    
}
