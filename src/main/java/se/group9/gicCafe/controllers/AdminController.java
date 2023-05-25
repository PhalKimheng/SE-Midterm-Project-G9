package se.group9.gicCafe.controllers;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import se.group9.gicCafe.config.FileUploadUtil;
import se.group9.gicCafe.model.Drink;
import se.group9.gicCafe.model.DrinkCategory;
import se.group9.gicCafe.model.Order;
import se.group9.gicCafe.model.OrderDetail;
import se.group9.gicCafe.service.DrinkCategoryService;
import se.group9.gicCafe.service.DrinkService;
import se.group9.gicCafe.service.OrderDetailService;
import se.group9.gicCafe.service.OrderService;
import se.group9.gicCafe.service.TableService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private DrinkService drinkService;
    @Autowired
    private TableService tableService;
    @Autowired
    private DrinkCategoryService drinkCategoryService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    public AdminController(DrinkService drinkService, TableService tableService,
            DrinkCategoryService drinkCategoryService, OrderService orderService,
            OrderDetailService orderDetailService) {
        super();
        this.drinkService = drinkService;
        this.tableService = tableService;
        this.drinkCategoryService = drinkCategoryService;
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/add-category")
    public String addCategoryScree(Model model) {
        model.addAttribute("ctg", new DrinkCategory());
        // model.addAttribute("isValid", true);
        return "newCategory";
    }

    @PostMapping("/add-category")
    public String addCategor(@ModelAttribute("ctg") DrinkCategory drinkCategory, Model model) {
        drinkCategoryService.savAndFlushDrinkCategory(drinkCategory);
        return "redirect:/admin/add-category";
    }

    @GetMapping("/add-drink")
    String addDrink(Model model) {
        model.addAttribute("categoryList", drinkCategoryService.getAllDrinkCategories());

        // model.addAttribute("drinkSizes", drinkSizeList);

        model.addAttribute("drink", new Drink(0, null, null, null, 1, 1, 1, null, null, null));
        return "newDrink";
    }

    @PostMapping("/add-drink")
    public String addDrink(@ModelAttribute("drink") Drink drink,
            @RequestParam("uploadImg") MultipartFile multipartFile,
            Model model) throws IOException {

        String fileName = multipartFile.getOriginalFilename();
        String uploadDir = "user-photos/Drink";

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        drink.setImage(uploadDir + "/" + fileName);
        System.out.println(drink);
        drinkService.saveDrink(drink);

        return "redirect:/admin/add-drink";
    }

}
