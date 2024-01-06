package com.example.projectforlyozin.service;

import com.example.projectforlyozin.dto.CustomerCreateEditDto;
import com.example.projectforlyozin.dto.CustomerReadDto;
import com.example.projectforlyozin.entity.Customer;
import com.example.projectforlyozin.entity.Order;
import com.example.projectforlyozin.mapper.CustomerCreateEditMapper;
import com.example.projectforlyozin.mapper.CustomerReadMapper;
import com.example.projectforlyozin.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerReadMapper customerReadMapper;
    private final CustomerCreateEditMapper customerCreateEditMapper;

    public CustomerReadDto create(CustomerCreateEditDto dto){
        Customer customer = customerRepository.save(customerCreateEditMapper.map(dto));
        //Добавляем новый ПУСТОЙ заказ ВО ВРЕМЯ РЕГИСТРАЦИИ пользователия
        customer.getOrders().add(new Order());
        return customerReadMapper.map(customer);
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
