package se.group9.gicCafe.service.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import se.group9.gicCafe.model.DrinkCategory;
import se.group9.gicCafe.repository.DrinkCategoryRepo;
import se.group9.gicCafe.service.DrinkCategoryService;

@Service
public class DrinkCategoryServiceImp implements DrinkCategoryService{
    private DrinkCategoryRepo drinkCategoryRepo;

    public DrinkCategoryServiceImp(DrinkCategoryRepo drinkCategoryRepo) {
        super();
        this.drinkCategoryRepo = drinkCategoryRepo;
    }

    @Override
    public List<DrinkCategory> getAllDrinkCategories() {
        return drinkCategoryRepo.findAll();
    }

    @Override
    public DrinkCategory saveDrinkCategory(DrinkCategory drinkCategory) {
        return drinkCategoryRepo.save(drinkCategory);
    }
}