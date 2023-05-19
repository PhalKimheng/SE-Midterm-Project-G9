package se.group9.gicCafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.group9.gicCafe.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
