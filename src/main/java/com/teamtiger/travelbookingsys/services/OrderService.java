package com.teamtiger.travelbookingsys.services;

import com.teamtiger.travelbookingsys.models.dtos.OrderDTO;
import org.aspectj.weaver.ast.Or;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);
}