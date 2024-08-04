package com.example.tacosapp.api;

import com.example.tacosapp.Taco;
import com.example.tacosapp.data.TacoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = {"http:/tacoapp:8080", "http://tacoapp.com"})
public class TacoApi {
    private final TacoService tacoService;

    public TacoApi(TacoService tacoService) {
        this.tacoService = tacoService;
    }

    @GetMapping(params = "recent")
    public List<Taco> recent(@RequestParam int page, @RequestParam int size) {
        return tacoService.findAll(PageRequest.of(page, size));
    }
}
