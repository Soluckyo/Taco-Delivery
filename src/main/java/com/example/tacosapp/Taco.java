package com.example.tacosapp;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Taco {

    private Long id;

    private Date createdAt = new Date();
    @NotNull
    @Size(min = 3, max = 20, message = "Name must be to least characters long")
    private String name;

    @NotNull
    @Size(min=1, message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

}
