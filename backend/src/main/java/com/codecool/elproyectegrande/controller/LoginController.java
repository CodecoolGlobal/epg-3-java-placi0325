package com.codecool.elproyectegrande.controller;

import com.codecool.elproyectegrande.controller.dto.NewClientDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {


    @PostMapping
    public void login(@RequestBody String username, String password) {
        System.out.println(username + " " + password);
    }
}
