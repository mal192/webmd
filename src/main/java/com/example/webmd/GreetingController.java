package com.example.webmd;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting
            (@RequestParam(name="name", required=false, defaultValue="World") String name, Model model)
    {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping //корень
    public String main
             (@RequestParam(name="par1", required=false, defaultValue="NO_NAME") String par1,
                          Map<String,Object> model)
    {
        model.put("some", "mustache: "+par1);
                return "main";
    }
}