package com.stygar.salon.repositories;

import com.stygar.salon.entities.GabinetZabieg;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GabinetZabiegRepository extends JpaRepository<GabinetZabieg, Long>{

    public GabinetZabieg getById(Long idzab);

    public GabinetZabieg findByZabiegId(Long idzab);
    
}
