package com.codecool.elproyectegrande.controller.dto;

import com.codecool.elproyectegrande.dao.model.Client;

public record NewProductDTO(String name, String description, double price) {
}
