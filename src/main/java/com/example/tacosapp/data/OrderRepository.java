package com.example.tacosapp.data;

import com.example.tacosapp.TacoOrder;
import com.example.tacosapp.Usr;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    List<TacoOrder> findByUserOrderByPlacedAtDesc(Usr user, Pageable pageable);
}
