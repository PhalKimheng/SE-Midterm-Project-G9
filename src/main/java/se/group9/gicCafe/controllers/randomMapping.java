package se.group9.gicCafe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class randomMapping {
    
    @GetMapping("/random")
    public String random1(){
        return "drinkSelection_orderInfo";
    }
}