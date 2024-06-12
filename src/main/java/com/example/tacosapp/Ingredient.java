package com.example.tacosapp;

import lombok.Data;

@Data
public class Ingredient {
    private final String id;
    private final String name;

    @Enumerated
    private final Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
