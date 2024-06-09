package com.example.tacosapp.repo;

import com.example.tacosapp.Ingredient;
import java.util.Optional;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(Long id);

    Ingredient save(Ingredient ingredient);
}
