package com.teamtiger.travelbookingsys.services;

import com.teamtiger.travelbookingsys.models.dtos.DetailedOrderDTO;
import com.teamtiger.travelbookingsys.models.dtos.OrderDTO;

public interface OrderService {
    DetailedOrderDTO createOrder(OrderDTO orderDTO);
}