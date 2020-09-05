package com.example.webmd;
//package com.example.webmd.rep;

import com.example.webmd.rep.MessageRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
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
    ///V2.0 -->
    @Autowired
    private MessageRep msRep;

    @GetMapping ("/mail") // просто вывод
    public String mail (Map<String,Object> model)
    {
    //    msRep.findAll(); alt+shift+v -> messages
        Iterable<MSG> messages = msRep.findAll();
        model.put("messages", messages);
        return "mail";
    }

    @PostMapping ("/mail") //добавление
    public String add(@RequestParam String text, @RequestParam String tag, Map<String,Object> model)
    {
        if ((text!="") || (tag!="")) {
            MSG message = new MSG(text, tag);
            msRep.save(message);
        }
        mail(model);
        return "mail";
    }

    @PostMapping ("/filtr") //фильтрация
    public String filtr(@RequestParam String filtr_tag, Map<String,Object> model)
    {
        Iterable<MSG> message;
        if (filtr_tag!=""){
            message = msRep.findByTag(filtr_tag);
        }else{
            message = msRep.findAll();
        }
        List<MSG> messages = msRep.findByTag(filtr_tag);
        model.put("messages", messages);
        return "mail";
    }
}