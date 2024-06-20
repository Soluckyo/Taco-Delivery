package com.example.tacosapp.repo;

import com.example.tacosapp.TacoOrder;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
