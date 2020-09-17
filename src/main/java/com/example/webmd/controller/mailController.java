package com.example.webmd.controller;
//package com.example.webmd.rep;

import com.example.webmd.MSG;

import com.example.webmd.User;
import com.example.webmd.rep.MessageRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.AWTException.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.io.IOException;
import javax.swing.filechooser.FileSystemView;

@Controller

public class mailController {
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
    public String add(
            @AuthenticationPrincipal User user, // <- правка V4
            @RequestParam String text,
            @RequestParam String tag,
            Map<String,Object> model)
    {
        if ((text!="") || (tag!="")) {
            MSG message = new MSG(text, tag, user); // <- V4
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
/// V2.5 ----->
 public static BufferedImage  img ()throws Exception{
        Robot robot = new Robot();
        Rectangle rec = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage img_ = robot.createScreenCapture(rec);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(img_, "jpeg", os);
    ImageIO.write(img_, "jpeg", new File("/home/mzg/1.jpg"));
   return img_;
 }

@GetMapping ("/scr") //
public String scr (Map<String,Object> model)
{
    String ii = "";
  try {
        BufferedImage im = img();
        ii = im.toString();
    } catch (Exception ex){
        ex.printStackTrace();
    }
    model.put("some", ii);
    return "scr";
}

////V3.0 ->>>>>
@GetMapping ("/home") //
public String home (Map<String,Object> model) {
                return "home";
}


} // end class