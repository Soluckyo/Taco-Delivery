package com.example.tacosapp.data;

import com.example.tacosapp.Usr;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Usr, Long> {
    Usr findByUsername(String username);
}
