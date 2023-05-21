package se.group9.gicCafe.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// import org.aspectj.apache.bcel.classfile.Module.Require;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import se.group9.gicCafe.config.FileUploadUtil;
import se.group9.gicCafe.model.Drink;
import se.group9.gicCafe.model.DrinkSize;
import se.group9.gicCafe.repository.CategoryRepository;
import se.group9.gicCafe.repository.DrinkRepository;
import se.group9.gicCafe.repository.DrinkSizeRepository;
import se.group9.gicCafe.repository.OrderDetailRepository;
import se.group9.gicCafe.repository.OrderRepository;

@Controller
public class DrinkController {
    // DrinkService drinkService = new DrinkService();
    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    DrinkSizeRepository drinkSizeRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
     OrderDetailRepository orderDetailRepository;

    @GetMapping("drink-selection-order-info")
    public String drinkSelection(Model model,
                                @RequestParam(value = "id",required = false, defaultValue = "0") long ctg_id    
    ){
        model.addAttribute("orderDetails", orderDetailRepository.findAll());
        model.addAttribute("categoryList", categoryRepository.findAll());
        if(ctg_id==0){
            model.addAttribute("drinkList", drinkRepository.findAll());
            return "drinkSelection_orderInfo";
        }
        model.addAttribute("drinkList", categoryRepository.findById(ctg_id).get().getDrink());
        return "drinkSelection_orderInfo";
    }

    // @GetMapping("drink-selection-order-info-add")
    // public String adddrinkSelection(Model model,
    //                             @RequestParam(value = "id",required = false, defaultValue = "1") long ctg_id    
    // ){
    //     model.addAttribute("drinkList", categoryRepository.findById(ctg_id).get().getDrink());
    //     model.addAttribute("orderDetails", orderDetailRepository.findAll());
    //     model.addAttribute("categoryList", categoryRepository.findAll());

    //     return "drinkSelection_orderInfo";
    // }




    @GetMapping("/addDrink")
    String addDrink(Model model) {
        model.addAttribute("categoryList", categoryRepository.findAll());

        // model.addAttribute("drinkSizes", drinkSizeList);

        model.addAttribute("drink", new Drink(12, null, null, null, null, null, null, null));
        return "newDrink";
    }


    @PostMapping("/addDrink")
    public String addDrink(@ModelAttribute("drink") Drink drink,
            @RequestParam("image") MultipartFile multipartFile,
            @RequestParam double sizeR,
            @RequestParam double sizeL,
            @RequestParam double sizeG,
            Model model) throws IOException {

        String fileName = multipartFile.getOriginalFilename();
        System.out.println(fileName);
        String uploadDir = "user-photos/Drink";

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        drink.setImagePath(uploadDir+"/"+fileName);
        drinkRepository.saveAndFlush(drink);

        List<DrinkSize> drinkSizeList = new ArrayList<>(Arrays.asList(
                                        new DrinkSize(0, "R", sizeR, drink, null),
                                        new DrinkSize(0, "L", sizeR, drink, null),
                                        new DrinkSize(0, "G", sizeR, drink, null))
                                        );

        drinkSizeRepository.saveAllAndFlush(drinkSizeList);
        return "redirect:/addDrink";
    }
}

