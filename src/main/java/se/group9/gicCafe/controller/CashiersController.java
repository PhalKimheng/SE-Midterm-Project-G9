package se.group9.gicCafe.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import se.group9.gicCafe.constants.CONSTANT;
import se.group9.gicCafe.model.Drink;
import se.group9.gicCafe.model.Order;
import se.group9.gicCafe.model.OrderDetail;
import se.group9.gicCafe.model.Tables;
import se.group9.gicCafe.model.User;
import se.group9.gicCafe.service.DrinkCategoryService;
import se.group9.gicCafe.service.DrinkService;
import se.group9.gicCafe.service.FoodCategoryService;
import se.group9.gicCafe.service.FoodService;
import se.group9.gicCafe.service.OrderDetailService;
import se.group9.gicCafe.service.OrderService;
import se.group9.gicCafe.service.TablesService;
import se.group9.gicCafe.service.UserService;

@Controller
@RequestMapping("/tables")
public class CashiersController {
    @Autowired
    private DrinkService drinkService;
    @Autowired
    private TablesService tableService;
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
    @Autowired
    private UserService userService;

    // table selection
    @GetMapping("")
    public String viewAllTables(Model model) {
        List<Tables> tables = tableService.getAllTables();
        model.addAttribute("tableList", tables);
        model.addAttribute("tableCount", tables.size());

        // Authentication authentication =
        // SecurityContextHolder.getContext().getAuthentication();
        // String username = authentication.getName();
        // User user =userService.getUserByUsername(username);

        return "TableS";
    }

    @GetMapping("/table")
    public String getTableInfo(@RequestParam int tid, Model model) {
        model.addAttribute("tableInfo", tableService.getTableByID(tid));

        Order order = tableService.getPendingOrderByTableID(tid);
        if (order != null) {

            model.addAttribute("orderInfo", order.getOrderDetail());
        }

        return "fragments/TableInfo :: table-order-info";
    }

    // ex:/tables/table1/drink-food-selection-order-info
    @GetMapping("/{table}/drink-food-selection-order-info")
    public String getDrinkSelectionView(@PathVariable("table") String table, Model model) {

        model.addAttribute("categoryList", drinkCategoryService.getAllDrinkCategories());
        model.addAttribute("drink_FoodList", drinkService.getAllDrinks());

        int tid = Integer.valueOf(table.replaceAll("table", ""));

        Order order = tableService.getPendingOrderByTableID(tid);

        if (order == null) {
            model.addAttribute("NoData", true);
            orderService.saveOrder(new Order(0, new java.util.Date(), null, 0, 0, 0, CONSTANT.Order_Status_Pending,
                    userService.getUserById(2), tableService.getTableByID(tid), null));

            Tables Tablee = tableService.getTableByID(tid);
            Tablee.setStatus(CONSTANT.Table_Status_Busy);
            tableService.saveTables(Tablee);

            return "drinkSelection_orderInfo";
        }

        List<OrderDetail> orderDetails = order.getOrderDetail();
        if (orderDetails.size() == 0) {
            model.addAttribute("NoData", true);
        } else {
            model.addAttribute("orderDetails", orderDetails);
        }

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
    public String GetFoodsData(String table,
            Model model,
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
        Order order = tableService.getPendingOrderByTableID(tid);
        if (order == null) {
            model.addAttribute("NoData", true);

            return "fragments/OrderDetail :: orderDetail_of_table";
        }

        List<OrderDetail> orderDetails = order.getOrderDetail();
        if (orderDetails.size() == 0) {
            model.addAttribute("NoData", true);
        } else {
            model.addAttribute("orderDetails", orderDetails);
        }

        return "fragments/OrderDetail :: orderDetail_of_table";
    }

    // ----------- selecting order(food/drink) ---------------
    @GetMapping("/{table}/drink-food-selection-order-info/add-drink")
    public String chooseDrinkView(Model model, @PathVariable String table, @RequestParam int id) {

        int tid = Integer.valueOf(table.replaceAll("table", ""));
        model.addAttribute("tid", tid);
        model.addAttribute("drinkOrFoodid", id);

        return "DrinkOrdering";
    }

    @GetMapping("/{table}/drink-food-selection-order-info/add-food")
    public String chooseFoodView(Model model, @PathVariable String table, @RequestParam int id) {

        int tid = Integer.valueOf(table.replaceAll("table", ""));
        model.addAttribute("tid", tid);
        model.addAttribute("drinkOrFoodid", id);

        // System.out.println(id);
        return "FoodOrdering";
    }


    // use for both food and drink, inerting to order detail Table
    @PostMapping("/{table}/drink-food-selection-order-info/add-{type}")
    public String saveOrder(Model model,
            @PathVariable("table") String table, @PathVariable String type, @RequestParam int id,
            @RequestParam(name = "size", required = false, defaultValue = "M") String size,
            @RequestParam(name = "cream", required = false, defaultValue = "false") boolean cream,
            @RequestParam(name = "sugar", required = false, defaultValue = "0") int sugar,
            @RequestParam int quantity) {
        System.out.println(sugar);
        int tid = Integer.valueOf(table.replaceAll("table", ""));
        
        Drink drink=drinkService.getDrinkById(id);
        double subTotal =drinkService.getDrinkPriceBySize(drink, size)*quantity;
        Order order=tableService.getPendingOrderByTableID(tid);

        OrderDetail orderDetail=new OrderDetail(0, sugar, cream, quantity, subTotal, size, order, drink, null);
        
        orderDetailService.saveAndFlushOrderDetail(orderDetail);
        // System.out.println(tid);
        return "redirect:/tables/table" + tid + "/drink-food-selection-order-info";
    }


    // ------print receipt ------------
    @GetMapping("/{table}/drink-food-selection-order-info/order-receipt")
    public String getReceiptView(@RequestParam int id, Model model) {
        model.addAttribute("order", orderService.getOrderByID(id));
        model.addAttribute("today", new Date(new java.util.Date().getTime()));

        return "printReceipt";
    }

}
