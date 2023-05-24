package se.group9.gicCafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import se.group9.gicCafe.model.Drink;
import se.group9.gicCafe.service.DrinkCategoryService;
import se.group9.gicCafe.service.DrinkService;

@Controller
public class DrinkController {
    @Autowired
    private DrinkService drinkService;

    @Autowired
    private DrinkCategoryService drinkCategoryService;

    public DrinkController(DrinkService drinkService) {
        super();
        this.drinkService = drinkService;
    }

    @GetMapping("/drinks")
    public String listDrinks(Model model) {
        model.addAttribute("drinks", drinkService.getAllDrinks());

        return "viewDrink";
    }

    @GetMapping("/drinks/detail/{id}")
    public String drinkDetail(@PathVariable int id, Model model) {
        model.addAttribute("drink", drinkService.getDrinkById(id));

        model.addAttribute("drinkCategories", drinkCategoryService.getAllDrinkCategories());
        return "drinkDetail";
    }

    @GetMapping("/drinks/edit/{id}")
    public String editDrinkForm(@PathVariable int id, Model model) {
        model.addAttribute("drink", drinkService.getDrinkById(id));

        model.addAttribute("drinkCategories", drinkCategoryService.getAllDrinkCategories());
        

        return "editDrink";
    }

    @PostMapping("/drinks/{id}")
    public String updateDrink(@PathVariable int id,
            @ModelAttribute("drink") Drink drink,
            Model model) {

        model.addAttribute("drinkCategories", drinkCategoryService.getAllDrinkCategories());

        // get student from database by id
        Drink existingDrink = drinkService.getDrinkById(id);
        existingDrink.setId(id);
        existingDrink.setCode(drink.getCode());
        existingDrink.setName(drink.getName());
        existingDrink.setDrinkCategory(drink.getDrinkCategory());
        existingDrink.setSize_R(drink.getSize_R());
        existingDrink.setSize_L(drink.getSize_L());
        existingDrink.setSize_G(drink.getSize_G());
        existingDrink.setNote(drink.getNote());

        // save updated Drink object
        drinkService.updateDrink(existingDrink);
        return "redirect:/drinks";
    }

    // handler method to handle delete student request

    @GetMapping("/drinks/{id}")
    public String deleteDrink(@PathVariable int id) {
        drinkService.deleteDrinkById(id);
        return "redirect:/drinks";
    }
}
