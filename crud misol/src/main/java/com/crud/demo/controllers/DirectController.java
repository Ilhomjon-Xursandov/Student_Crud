package com.crud.demo.controllers;

import com.sun.istack.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DirectController {

    @GetMapping("/add")
    public String getStudent(){
        return "add";
    }


}
