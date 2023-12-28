package com.teamtiger.travelbookingsys.services;

import com.teamtiger.travelbookingsys.exceptions.BookingIdInvalidException;
import com.teamtiger.travelbookingsys.exceptions.BookingNotFoundException;
import com.teamtiger.travelbookingsys.exceptions.CustomerIdInvalidException;
import com.teamtiger.travelbookingsys.exceptions.CustomerNotFoundException;
import com.teamtiger.travelbookingsys.models.dtos.OrderDTO;
import com.teamtiger.travelbookingsys.models.entities.Booking;
import com.teamtiger.travelbookingsys.models.entities.Customer;
import com.teamtiger.travelbookingsys.models.entities.Order;
import com.teamtiger.travelbookingsys.repositories.BookingRepository;
import com.teamtiger.travelbookingsys.repositories.CustomerRepository;
import com.teamtiger.travelbookingsys.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order orderEntity = convertDTOToEntity(orderDTO);
        Order savedOrder = orderRepository.save(orderEntity);
        log.info("Order {} was saved", savedOrder.getId());
        return convertEntityToDTO(savedOrder);
    }

    public OrderDTO convertEntityToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setPeopleCount(order.getPeopleCount());
        orderDTO.setBookingId(order.getBookingId().getId());
        orderDTO.setCustomerId(order.getCustomerId().getId());
        return orderDTO;
    }

    public Order convertDTOToEntity(OrderDTO orderDTO) {
        Order orderEntity = new Order();
        orderEntity.setPeopleCount(orderDTO.getPeopleCount());

        if (orderDTO.getBookingId() != null) {
            Optional<Booking> bookingEntityOptional = bookingRepository.findById(orderDTO.getBookingId());
            if (bookingEntityOptional.isPresent()) {
                orderEntity.setBookingId(bookingEntityOptional.get());
            } else {
                throw new BookingNotFoundException("Booking not found! ");
            }
        } else {
            throw new BookingIdInvalidException("Invalid Booking id! ");
        }

        if (orderDTO.getCustomerId() != null) {
            Optional<Customer> customerEntityOptional = customerRepository.findById(orderDTO.getCustomerId());
            if (customerEntityOptional.isPresent()) {
                orderEntity.setCustomerId(customerEntityOptional.get());
            } else {
                throw new CustomerNotFoundException("Customer not found! ");
            }
        } else {
            throw new CustomerIdInvalidException("Invalid Customer id! ");
        }
        return orderEntity;
    }
}