package com.example.tacosapp;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class IngredientRef {
    private final String ingredient;
}
