package com.example.projectforlyozin.mapper;

import com.example.projectforlyozin.dto.CustomerReadDto;
import com.example.projectforlyozin.dto.OrderReadDto;
import com.example.projectforlyozin.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomerReadMapper implements Mapper<Customer, CustomerReadDto>{

    private final OrderReadMapper orderReadMapper;

    @Override
    public CustomerReadDto map(Customer object) {
        List<OrderReadDto> orders = object.getOrders().stream()
                .map((orderReadMapper::map))
                .collect(Collectors.toList());
        return new CustomerReadDto(
                object.getCustomerId(),
                object.getFirstName(),
                object.getLastName(),
                object.getPhoneNumber(),
                object.getCity(),
                object.getStreet(),
                object.getHouse(),
                object.getApartment(),
                object.getUsername(),
                object.getPassword(),
                object.getRole(),
                orders);
    }
}
