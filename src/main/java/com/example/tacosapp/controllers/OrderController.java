package com.example.tacosapp.controllers;

import com.example.tacosapp.TacoOrder;
import com.example.tacosapp.User;
import com.example.tacosapp.data.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.validation.Errors;
import org.springframework.ui.Model;

import java.security.Principal;

@Controller
@Slf4j
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private final UserRepository userRepository;

    public OrderController(UserRepository userRepository) {
        this.userRepository = userRepository;
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

        User user = (User) authentication.getPrincipal();
        order.setUser(user);

        return "redirect:/";
    }
}
