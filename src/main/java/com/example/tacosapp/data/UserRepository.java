package com.example.tacosapp.data;

import com.example.tacosapp.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Long> {
    Users findByUsername(String username);
}
