package se.group9.gicCafe.service.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import se.group9.gicCafe.model.Order;
import se.group9.gicCafe.repository.OrderRepo;
import se.group9.gicCafe.service.OrderService;

@Service
public class OrderServiceImp implements OrderService {

    private OrderRepo orderRepo;

    public OrderServiceImp(OrderRepo orderRepo) {
        super();
        this.orderRepo=orderRepo;
        }

    @Override
    public Order getOrderByID(int id) {
        return orderRepo.findById(id).get();
    }


    @Override
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }
}
