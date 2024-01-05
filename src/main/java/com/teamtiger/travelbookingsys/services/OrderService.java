package com.teamtiger.travelbookingsys.services;

import com.teamtiger.travelbookingsys.models.dtos.DetailedOrderDTO;
import com.teamtiger.travelbookingsys.models.dtos.OrderDTO;

import java.util.List;

public interface OrderService {
    DetailedOrderDTO createOrder(OrderDTO orderDTO);
    List<DetailedOrderDTO> getAllOrders();
    List<DetailedOrderDTO> getOrderByCustomerId(Long id);
    void deleteOrder(Long id);
}