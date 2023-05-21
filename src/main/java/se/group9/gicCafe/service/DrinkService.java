package se.group9.gicCafe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.group9.gicCafe.model.Drink;
import se.group9.gicCafe.repository.DrinkRepository;

@Service
public class DrinkService {
    @Autowired
    DrinkRepository drinkRepository;

    public List<Drink> getAllDrink(){
        return drinkRepository.findAll();
    }

    public Drink getDrinkByID(long id){
        return drinkRepository.getReferenceById(id);
    }

    public void addNewDrink(Drink drink){
        drinkRepository.saveAndFlush(drink);
    }

    public void updateDrink(Drink drink){
        drinkRepository.saveAndFlush(drink);
    }

    public void deleteDrink(long id){
        drinkRepository.deleteById(id);
    }
}
