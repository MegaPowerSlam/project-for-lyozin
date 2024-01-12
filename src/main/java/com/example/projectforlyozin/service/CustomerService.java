package com.example.projectforlyozin.service;

import com.example.projectforlyozin.dto.CustomerCreateEditDto;
import com.example.projectforlyozin.dto.CustomerReadDto;
import com.example.projectforlyozin.dto.OrderReadDto;
import com.example.projectforlyozin.entity.Customer;
import com.example.projectforlyozin.entity.Order;
import com.example.projectforlyozin.mapper.CustomerCreateEditMapper;
import com.example.projectforlyozin.mapper.CustomerReadMapper;
import com.example.projectforlyozin.mapper.OrderReadMapper;
import com.example.projectforlyozin.repository.CustomerRepository;
import com.example.projectforlyozin.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final CustomerReadMapper customerReadMapper;
    private final OrderReadMapper orderReadMapper;
    private final CustomerCreateEditMapper customerCreateEditMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public CustomerReadDto create(CustomerCreateEditDto dto) throws Exception{
        Customer customer = customerCreateEditMapper.map(dto);
        if(customer.getFirstName().equals("") || customer.getLastName().equals("") || customer.
                getUsername().equals("") || customer.getPassword().equals("") || customer.getPhoneNumber().equals("") || customer.
                getCity().equals("") || customer.getCity().equals("") || customer.getHouse().equals(0) || customer.
                getApartment().equals(0)) throw new Exception("");
        customerRepository.save(customer);
        customer.setPassword(passwordEncoder.encode(dto.getPassword()));
        Order order = new Order();
        order.setCustomer(customer);
        customer.getOrders().add(order);
        orderRepository.save(order);
//        Optional<Customer> customer1 = customerRepository.findById(customer.getCustomerId());
//        Добавляем новый ПУСТОЙ заказ ВО  ВРЕМЯ РЕГИСТРАЦИИ пользователия
        return customerReadMapper.map(customerRepository.save(customer));
    }

    @Transactional
    public List<OrderReadDto> getAllOrders(Integer customerId){
        Customer customer = customerRepository.findById(customerId).get();
        return customer.getOrders().stream()
                .map(entity ->
                        orderReadMapper.map(entity)
                )
                .collect(Collectors.toList());
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
