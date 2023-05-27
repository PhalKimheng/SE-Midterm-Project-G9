package se.group9.gicCafe.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import se.group9.gicCafe.service.FoodCategoryService;
import se.group9.gicCafe.service.FoodService;
import se.group9.gicCafe.service.OrderDetailService;
import se.group9.gicCafe.service.OrderService;
import se.group9.gicCafe.service.TableService;

@Controller
@RequestMapping("/tables")
public class UsersController {
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
    @Autowired
    private FoodCategoryService foodCategoryService;
    @Autowired
    private FoodService foodService;

    public UsersController(DrinkService drinkService, TableService tableService,
            DrinkCategoryService drinkCategoryService, OrderService orderService,
            OrderDetailService orderDetailService, FoodCategoryService foodCategoryService,
            FoodService foodService) {
        super();
        this.drinkService = drinkService;
        this.tableService = tableService;
        this.drinkCategoryService = drinkCategoryService;
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
        this.foodCategoryService = foodCategoryService;
        this.foodService = foodService;
    }

    @GetMapping("")
    public String viewAllTables(Model model) {
        model.addAttribute("tableList", tableService.getAllTables());

        return "tables";
    }

    @PostMapping("/{table}")
    public String getTableInfo(@PathVariable("table") String table, Model model) {
        int tid = Integer.valueOf(table.replaceAll("table", ""));
        model.addAttribute("table", tableService.getTableByID(tid));

        return "redirect:/tables";
    }

    @GetMapping("/{table}/drink-food-selection-order-info")
    public String getDrinkSelectionView(@PathVariable("table") String table, Model model) {

        int tid = Integer.valueOf(table.replaceAll("table", ""));
        model.addAttribute("tableNumber", tid);

        List<OrderDetail> orderDetail = orderService.getOrderByID(tid).getOrderDetail();
        if (orderDetail.size() == 0)
            model.addAttribute("NoData", true);
        model.addAttribute("orderDetails", orderDetail);

        model.addAttribute("categoryList", drinkCategoryService.getAllDrinkCategories());
        model.addAttribute("drink_FoodList", drinkService.getAllDrinks());

        return "drinkSelection_orderInfo";
    }

    // -------get category - default is drink category-------------
    @GetMapping("/{table}/drink-food-selection-order-info/get-category")
    public String getCategory(@RequestParam String ctg_name, Model model) {
        if (ctg_name.toLowerCase().equals("drink")) {
            model.addAttribute("categoryList", drinkCategoryService.getAllDrinkCategories());
        } else {
            model.addAttribute("categoryList", foodCategoryService.getAllFoodCategories());
        }
        return "fragments/CategoryData :: category_data";
    }

    // ------- get data about foods or drinks---------------------------
    @GetMapping("/{table}/drink-food-selection-order-info/get-foods")
    public String GetFoodsData(String table, Model model,
            @RequestParam(value = "id") int ctg_id) {

        if (ctg_id == 0) {
            model.addAttribute("drink_FoodList", foodService.getAllFoods());
        } else
            model.addAttribute("drink_FoodList",
                    foodCategoryService.getFoodCategoryByID(ctg_id).getFood());
        return "fragments/Drink_FoodData :: drink_food";
    }

    @GetMapping("/{table}/drink-food-selection-order-info/get-drinks")
    public String GetdrinkData(String table, Model model,
            @RequestParam(value = "id") int ctg_id) {

        if (ctg_id == 0) {
            model.addAttribute("drink_FoodList", drinkService.getAllDrinks());
        } else
            model.addAttribute("drink_FoodList",
                    drinkCategoryService.getDrinkCategoryByID(ctg_id).getDrink());

        return "fragments/Drink_FoodData :: drink_food";
    }

    // ---------- delete order detail-------------------

    @GetMapping("/{table}/drink-food-selection-order-info/delete-order")
    public String deleteOrderDetail(Model model, @PathVariable("table") String table, @RequestParam int id) {

        orderDetailService.deleteOrderDetailByID(id);

        int tid = Integer.valueOf(table.replaceAll("table", ""));
        List<OrderDetail> orderDetail = orderService.getOrderByID(tid).getOrderDetail();
        if (orderDetail.size() == 0)
            model.addAttribute("NoData", true);
        model.addAttribute("orderDetails", orderDetail);

        return "fragments/OrderDetail :: orderDetail_of_table";
    }

    // ----------------------------

    // @GetMapping("/add-category")
    // public String addCategoryScree(Model model) {
    // model.addAttribute("ctg", new DrinkCategory());
    // // model.addAttribute("isValid", true);
    // return "newCategory";
    // }

    // @PostMapping("/add-category")
    // public String addCategor(@ModelAttribute("ctg") DrinkCategory drinkCategory,
    // Model model) {
    // drinkCategoryService.savAndFlushDrinkCategory(drinkCategory);
    // return "redirect:/tables/add-category";
    // }

    //
}
