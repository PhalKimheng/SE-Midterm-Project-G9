package se.group9.gicCafe.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import se.group9.gicCafe.repository.UserRepo;
import se.group9.gicCafe.service.UserService;
import se.group9.gicCafe.model.User;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping("/cashiers")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "viewCashier";
    }

    @GetMapping("/cashiers/detail/{id}")
	public String cashierDetail(@PathVariable int id, Model model) {
		model.addAttribute("user", userService.getUserById(id));
		return "cashierDetail";
	}

    @GetMapping("/cashiers/edit/{id}")
	public String editCashierForm(@PathVariable int id, Model model) {
		model.addAttribute("user", userService.getUserById(id));
		return "editCashier";
	}

	@PostMapping("/cashiers/{id}")
	public String updateCashier(@PathVariable int id,
			@ModelAttribute("user") User user,
			Model model) {
		
		// get student from database by id
		User existingUser = userService.getUserById(id);
		existingUser.setId(id);
		existingUser.setFirstname(user.getFirstname());
		existingUser.setLastname(user.getLastname());
		existingUser.setGender(user.getGender());
        existingUser.setDate_of_birth(user.getDate_of_birth());
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
		
		// save updated User object
		userService.updateUser(existingUser);
		return "redirect:/cashiers";		
	}
	
	// handler method to handle delete student request
	
	@GetMapping("/cashiers/{id}")
	public String deleteCashier(@PathVariable int id) {
		userService.deleteUserById(id);
		return "redirect:/cashiers";
	}
}
