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

    public UsersController(DrinkService drinkService, TableService tableService,
            DrinkCategoryService drinkCategoryService, OrderService orderService,
            OrderDetailService orderDetailService) {
        super();
        this.drinkService = drinkService;
        this.tableService = tableService;
        this.drinkCategoryService = drinkCategoryService;
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
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
        if (orderDetail.size()==0)  model.addAttribute("NoData", true);
        model.addAttribute("orderDetails", orderDetail);

        model.addAttribute("categoryList", drinkCategoryService.getAllDrinkCategories());
        model.addAttribute("drinkList", drinkService.getAllDrinks());

        return "drinkSelection_orderInfo";
    }

    @GetMapping("/{table}/drink-food-selection-order-info/get")
    public String GetdrinkData(String table, Model model,
            @RequestParam(value = "id") long ctg_id) {

        // int tid = Integer.valueOf(table.replaceAll("table", ""));
        // model.addAttribute("orderDetails",
        // orderService.getOrderByID(tid).getOrderDetail());
        // model.addAttribute("categoryList",
        // drinkCategoryService.getAllDrinkCategories());

        if (ctg_id == 0) {
            model.addAttribute("drinkList", drinkService.getAllDrinks());
        } else
            model.addAttribute("drinkList",
                    drinkCategoryService.getDrinkCategoryByID((int) ctg_id).getDrink());
        return "fragments/Drink_FoodData :: drink_food";
    }

    @GetMapping("/{table}/drink-food-selection-order-info/delete")
    public String delete(Model model, @PathVariable("table") String table, @RequestParam int id) {

        orderDetailService.deleteOrderDetailByID(id);

        int tid = Integer.valueOf(table.replaceAll("table", ""));
        List<OrderDetail> orderDetail = orderService.getOrderByID(tid).getOrderDetail();
        if (orderDetail.size()==0)  model.addAttribute("NoData", true);
        model.addAttribute("orderDetails", orderDetail);

        return "fragments/OrderDetail :: orderDetail_of_table";
    }


    // ----------------------------

    @GetMapping("/add-category")
    public String addCategoryScree(Model model) {
        model.addAttribute("ctg", new DrinkCategory());
        // model.addAttribute("isValid", true);
        return "newCategory";
    }

    @PostMapping("/add-category")
    public String addCategor(@ModelAttribute("ctg") DrinkCategory drinkCategory, Model model) {
        drinkCategoryService.savAndFlushDrinkCategory(drinkCategory);
        return "redirect:/tables/add-category";
    }

    //
}
