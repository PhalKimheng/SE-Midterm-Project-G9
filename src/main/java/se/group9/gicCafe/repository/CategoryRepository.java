package se.group9.gicCafe.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import se.group9.gicCafe.model.DrinkCategory;

public interface CategoryRepository extends JpaRepository<DrinkCategory, Long>{
    
}
