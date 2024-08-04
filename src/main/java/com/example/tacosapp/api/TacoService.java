package com.example.tacosapp.api;

import com.example.tacosapp.Taco;
import com.example.tacosapp.data.TacoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TacoService {
    private final TacoRepository tacoRepository;

    public TacoService(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    public List<Taco> findAll(PageRequest pageRequest){
        Page<Taco> page = tacoRepository.findAll(pageRequest);
        return page.getContent();
    }

}
