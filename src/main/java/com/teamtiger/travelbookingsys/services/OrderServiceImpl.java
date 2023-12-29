package com.teamtiger.travelbookingsys.services;

import com.teamtiger.travelbookingsys.exceptions.booking.BookingIdInvalidException;
import com.teamtiger.travelbookingsys.exceptions.booking.BookingNotFoundException;
import com.teamtiger.travelbookingsys.exceptions.customer.CustomerIdInvalidException;
import com.teamtiger.travelbookingsys.exceptions.customer.CustomerNotFoundException;
import com.teamtiger.travelbookingsys.exceptions.order.OrderPeopleCountExceedsLimitException;
import com.teamtiger.travelbookingsys.models.dtos.DetailedOrderDTO;
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
    public DetailedOrderDTO createOrder(OrderDTO orderDTO) {
        Order orderEntity = convertDTOToEntity(orderDTO);
        if (!isBookingValid(orderEntity.getBookingId().getId(), orderEntity.getPeopleCount())) {
            throw new OrderPeopleCountExceedsLimitException("People count exceeds the limit of available spots");
        }
        Order savedOrder = orderRepository.save(orderEntity);

        log.info("Order {} was saved", savedOrder.getId());

        double totalPrice = calculateTotalPrice(savedOrder.getPeopleCount(), savedOrder.getBookingId().getTravelPackage().getPrice());
        DetailedOrderDTO detailedOrderDTO = convertEntityToDetailedDTO(savedOrder, totalPrice);
        return detailedOrderDTO;
    }

    public double calculateTotalPrice(int peopleCount, double pricePerPerson) {
        return peopleCount * pricePerPerson;
    }

    public boolean isBookingValid(Long bookingId, int newPeopleCount) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found!"));
        int totalPeopleCountInOrders = orderRepository.getTotalPeopleCountByBookingId(bookingId);
        int remainingSpots = booking.getAvailableSpots() - totalPeopleCountInOrders;
        return newPeopleCount <= remainingSpots;
    }

    public DetailedOrderDTO convertEntityToDetailedDTO(Order order, double totalPrice) {
        DetailedOrderDTO detailedOrderDTO = new DetailedOrderDTO();
        detailedOrderDTO.setId(order.getId());
        detailedOrderDTO.setBookingId(order.getBookingId().getId());
        detailedOrderDTO.setCustomerId(order.getCustomerId().getId());
        detailedOrderDTO.setPeopleCount(order.getPeopleCount());
        detailedOrderDTO.setTotalPrice(totalPrice);
        return detailedOrderDTO;
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