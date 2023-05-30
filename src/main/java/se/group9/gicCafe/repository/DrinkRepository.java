package se.group9.gicCafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.group9.gicCafe.model.Drink;

public interface DrinkRepository extends JpaRepository<Drink, Long> {

}