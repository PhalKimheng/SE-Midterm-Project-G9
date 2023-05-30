package se.group9.gicCafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.group9.gicCafe.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}