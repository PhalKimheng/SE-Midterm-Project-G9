package se.group9.gicCafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.group9.gicCafe.model.DrinkSize;

public interface DrinkSizeRepository  extends JpaRepository<DrinkSize, Long>{
    
}
