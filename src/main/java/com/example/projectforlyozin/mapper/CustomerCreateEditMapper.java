package com.example.projectforlyozin.mapper;

import com.example.projectforlyozin.dto.CustomerCreateEditDto;
import com.example.projectforlyozin.entity.Customer;
import com.example.projectforlyozin.entity.Order;
import com.example.projectforlyozin.entity.Role;
import com.example.projectforlyozin.repository.CustomerRepository;
import com.example.projectforlyozin.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class CustomerCreateEditMapper implements Mapper<CustomerCreateEditDto, Customer>{

    private final OrderRepository orderRepository;
    @Override
    public Customer map(CustomerCreateEditDto fromObject, Customer toObject) {
        copy(fromObject, toObject);
        return toObject;
    }
    @Override
    public Customer map(CustomerCreateEditDto object) {
        Customer customer = new Customer();
        copy(object, customer);
        return customer;
    }

    private void copy (CustomerCreateEditDto fromObject, Customer toObject){
        toObject.setCustomerId(fromObject.getCustomerId());
        toObject.setFirstName(fromObject.getFirstName());
        toObject.setLastName(fromObject.getLastName());
        toObject.setPhoneNumber(fromObject.getPhoneNumber());
        toObject.setCity(fromObject.getCity());
        toObject.setStreet(fromObject.getStreet());
        toObject.setHouse(fromObject.getHouse());
        toObject.setApartment(fromObject.getApartment());
        toObject.setUsername(fromObject.getUsername());
        toObject.setPassword(fromObject.getPassword());
        toObject.setRole(Role.valueOf(fromObject.getRole()));
        if (fromObject.getOrderIds() == null){

        } else {
            toObject.setOrders(getOrders(fromObject.getOrderIds(), toObject.getOrders()));
        }
    }

    private List<Order> getOrders(List<Integer> orderIds, List<Order> orders){
        for(int i = 0; i < orderIds.size(); i++){
            Integer id = orderIds.get(i);
            orders.remove(i);
            Order order = Optional.ofNullable(id)
                .flatMap(orderRepository::findById)
                .orElse(null);
            orders.add(i, order);
        }
        return orders;
    }
}
