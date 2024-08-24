package com.example.tacosapp.api;

import com.example.tacosapp.Taco;
import com.example.tacosapp.TacoOrder;
import com.example.tacosapp.data.OrderRepository;
import com.example.tacosapp.data.TacoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = {"http:/tacoapp:8080", "http://tacoapp.com"})
public class TacoApi {
    private final TacoService tacoService;
    private final TacoRepository tacoRepository;
    private final OrderRepository orderRepository;

    public TacoApi(TacoService tacoService, TacoRepository tacoRepository, OrderRepository orderRepository) {
        this.tacoService = tacoService;
        this.tacoRepository = tacoRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping(params = "recent")
    public List<Taco> recent(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return tacoService.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable Long id) {
        Optional<Taco> optTaco = tacoRepository.findById(id);
        if (optTaco.isPresent()) {
            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco){
        return tacoRepository.save(taco);
    }

    @PutMapping(path = "/{orderId}", consumes = "application/json")
    public TacoOrder putOrder(@PathVariable Long orderId, @RequestBody TacoOrder order){
        order.setId(orderId);
        return orderRepository.save(order);

    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public TacoOrder patchOrder(@PathVariable Long orderId, @RequestBody TacoOrder patch){
        TacoOrder order = orderRepository.findById(orderId).get();
        if(patch.getDeliveryName() != null){
            order.setDeliveryName(patch.getDeliveryName());
        }
        if(patch.getDeliveryStreet() != null){
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if(patch.getDeliveryCity() != null){
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if(patch.getDeliveryState() != null){
            order.setDeliveryState(patch.getDeliveryState());
        }
        if(patch.getDeliveryZip() != null){
            order.setDeliveryZip(patch.getDeliveryZip());
        }
        if(patch.getCcNumber() != null){
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null){
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null){
            order.setCcCVV(patch.getCcCVV());
        }
        return orderRepository.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long orderId){
        try{
            orderRepository.deleteById(orderId);
        }catch (EmptyResultDataAccessException e){}
    }
}
