package se.group9.gicCafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.group9.gicCafe.model.User;


public interface UserRepository extends JpaRepository<User, Long>{
    
}
