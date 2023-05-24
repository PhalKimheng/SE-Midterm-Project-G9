package se.group9.gicCafe.service.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import se.group9.gicCafe.model.Drink;
import se.group9.gicCafe.repository.DrinkRepo;
import se.group9.gicCafe.service.DrinkService;

@Service
public class DrinkServiceImp implements DrinkService{
    private DrinkRepo drinkRepo;

    public DrinkServiceImp(DrinkRepo drinkRepo) {
        super();
        this.drinkRepo = drinkRepo;
    }

    @Override
    public List<Drink> getAllDrinks() {
        return drinkRepo.findAll();
    }

    @Override
    public Drink saveDrink(Drink Drink) {
        return drinkRepo.save(Drink);
    }

    @Override
    public Drink getDrinkById(int id) {
		return drinkRepo.findById(id).get();
    }

    @Override
	public Drink updateDrink(Drink Drink) {
		return drinkRepo.save(Drink);
	}

	@Override
	public void deleteDrinkById(int id) {
		drinkRepo.deleteById(id);	
	}
}
