package se.group9.gicCafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import se.group9.gicCafe.service.FoodService;

@Controller
public class FoodController {
    private FoodService foodService;

    public FoodController(FoodService foodService) {
        super();
        this.foodService = foodService;
    }

    @GetMapping("/foods")
    public String listFoods(Model model) {
        model.addAttribute("foods", foodService.getAllFoods());
        return "viewFood";
    }

    @GetMapping("/foods/detail/{id}")
	public String foodDetail(@PathVariable int id, Model model) {
		model.addAttribute("food", foodService.getFoodById(id));
		return "foodDetail";
	}
}
