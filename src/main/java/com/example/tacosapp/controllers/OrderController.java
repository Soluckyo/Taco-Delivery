package com.example.tacosapp.controllers;

import com.example.tacosapp.OrderProps;
import com.example.tacosapp.TacoOrder;
import com.example.tacosapp.Usr;
import com.example.tacosapp.data.OrderRepository;
import com.example.tacosapp.data.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.validation.Errors;
import org.springframework.ui.Model;

@Controller
@Slf4j
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private OrderProps props;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public OrderController(UserRepository userRepository, OrderRepository orderRepository, OrderProps props) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.props = props;
    }

    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("tacoOrder", new TacoOrder());
        return "orderForm";
    }


    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
                               SessionStatus sessionStatus,
                               Authentication authentication){
        if (errors.hasErrors()) {
            return "orderForm";
        }
        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();

        Usr user = (Usr) authentication.getPrincipal();
        order.setUser(user);

        return "redirect:/";
    }

    @GetMapping
    public String orderForUser(@AuthenticationPrincipal Usr user, Model model) {

        Pageable pageable = PageRequest.of(0, props.getPageSize());
        model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));

        return "orderList";
    }
}
