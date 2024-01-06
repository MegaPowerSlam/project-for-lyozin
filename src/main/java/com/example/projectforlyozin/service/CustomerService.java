package com.example.projectforlyozin.service;

import com.example.projectforlyozin.dto.CustomerCreateEditDto;
import com.example.projectforlyozin.dto.CustomerReadDto;
import com.example.projectforlyozin.entity.Customer;
import com.example.projectforlyozin.entity.Order;
import com.example.projectforlyozin.mapper.CustomerCreateEditMapper;
import com.example.projectforlyozin.mapper.CustomerReadMapper;
import com.example.projectforlyozin.repository.CustomerRepository;
import com.example.projectforlyozin.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final CustomerReadMapper customerReadMapper;
    private final CustomerCreateEditMapper customerCreateEditMapper;

    @Transactional
    public CustomerReadDto create(CustomerCreateEditDto dto){
        Customer customer = customerRepository.save(customerCreateEditMapper.map(dto));
        Order order = new Order();
        order.setCustomer(customer);
        customer.getOrders().add(order);
        orderRepository.save(order);
//        Optional<Customer> customer1 = customerRepository.findById(customer.getCustomerId());
//        Добавляем новый ПУСТОЙ заказ ВО  ВРЕМЯ РЕГИСТРАЦИИ пользователия
        return customerReadMapper.map(customerRepository.save(customer));
    }

    public CustomerReadDto read(Integer id){
        return customerReadMapper.map(customerRepository.findById(id).get());
    }

    public CustomerReadDto update(CustomerCreateEditDto dto, Integer id){
        Customer customer = customerRepository.findById(id).get();
        return customerReadMapper.map(customerCreateEditMapper.map(dto, customer));
    }

    public void delete(Integer id){
        customerRepository.delete(customerRepository.findById(id).get());
    }
}
