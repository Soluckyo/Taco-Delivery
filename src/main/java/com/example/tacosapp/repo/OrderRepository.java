package com.example.tacosapp.repo;

import com.example.tacosapp.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
