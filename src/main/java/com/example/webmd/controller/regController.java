package com.example.webmd.controller;
import com.example.webmd.MSG;
import com.example.webmd.rep.MessageRep;
import com.example.webmd.Role;
import com.example.webmd.User;
import com.example.webmd.rep.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;
///V3  --->
@Controller
public class regController {
    @Autowired
    private UserRep userRep;

    @GetMapping("/reg")
    public String reg(){
        return "reg";
    }

    @PostMapping("/reg")
    public  String addUser(User user, Map<String, Object> model){
        User userFromDb = userRep.findByUsername(user.getUsername());
        if (userFromDb!=null){
            model.put("message", "User Exists");
            return  "reg";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
            //user.setGroups(Collections.singleton(Role.USER)); //Лучше групп
        userRep.save(user);
        return "redirect:/login";

    }
    ////--<

} //end class
