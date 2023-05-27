package se.group9.gicCafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import se.group9.gicCafe.service.DrinkCategoryService;

@Controller
public class DrinkCategoryController {
    private DrinkCategoryService drinkCategoryService;
    public DrinkCategoryController(DrinkCategoryService drinkCategoryService) {
        super();
        this.drinkCategoryService = drinkCategoryService;
    }
}
