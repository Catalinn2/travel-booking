package com.teamtiger.travelbookingsys.controllers;

import com.teamtiger.travelbookingsys.models.dtos.DetailedOrderDTO;
import com.teamtiger.travelbookingsys.models.dtos.OrderDTO;
import com.teamtiger.travelbookingsys.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<DetailedOrderDTO> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(orderService.createOrder(orderDTO));
    }

    @GetMapping
    public ResponseEntity<List<DetailedOrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<DetailedOrderDTO>> getOrderByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(orderService.getOrderByCustomerId(customerId));
    }
    @DeleteMapping("{orderId}")
    public ResponseEntity<String> deleteOrderById(@PathVariable Long orderId){
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok("Order with id " + orderId + " has been successfully deleted!");
    }
}