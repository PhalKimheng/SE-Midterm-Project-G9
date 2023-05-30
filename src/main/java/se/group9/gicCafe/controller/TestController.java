package se.group9.gicCafe.controller;

import org.springframework.stereotype.Controller;
// import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

    @GetMapping(path = "/order")
    public Object model() {
        return new ModelAndView("order");
    }

    @GetMapping(path = "/")
    public String test(Model model) {
        model.addAttribute("drink", "Iced Latte");
        return "test";
    }
}
