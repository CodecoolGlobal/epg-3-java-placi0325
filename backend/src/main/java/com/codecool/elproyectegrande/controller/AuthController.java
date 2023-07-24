package com.codecool.elproyectegrande.controller;

import com.codecool.elproyectegrande.controller.dto.NewClientDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthController {

    @PostMapping("/login")
    public void login(@RequestBody NewClientDTO clientDTO) {
    }
}
