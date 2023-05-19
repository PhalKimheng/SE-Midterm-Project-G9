package se.group9.gicCafe.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import se.group9.gicCafe.model.User;
import se.group9.gicCafe.repository.UserRepository;

@Controller
public class UserController {
    @Autowired
    public UserRepository userRepo;

    @GetMapping("/home/cashier")
    public User getCashierInfo(@RequestParam(value = "id") Long id){
        Optional<User> user= userRepo.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }

    @PostMapping("/")
    public String addCashier(@RequestParam(value="cashier") User cashier){
        User u=new User(10, "123", null, null, null, null, null, null, null, null);
        userRepo.save(u);

        return "HOOOO";
    }

}
