package com.example.projectforlyozin.service;

import com.example.projectforlyozin.dto.CustomerCreateEditDto;
import com.example.projectforlyozin.dto.CustomerReadDto;
import com.example.projectforlyozin.dto.DeliveryListCreateEditDto;
import com.example.projectforlyozin.dto.DeliveryListReadDto;
import com.example.projectforlyozin.entity.Customer;
import com.example.projectforlyozin.entity.DeliveryList;
import com.example.projectforlyozin.mapper.CustomerCreateEditMapper;
import com.example.projectforlyozin.mapper.CustomerReadMapper;
import com.example.projectforlyozin.mapper.DeliveryListCreateEditMapper;
import com.example.projectforlyozin.mapper.DeliveryListReadMapper;
import com.example.projectforlyozin.repository.CustomerRepository;
import com.example.projectforlyozin.repository.DeliveryListRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeliveryListService {

    private final DeliveryListRepository deliveryListRepository;
    private final DeliveryListReadMapper deliveryListReadMapper;
    private final DeliveryListCreateEditMapper deliveryListCreateEditMapper;

    public DeliveryListReadDto create(DeliveryListCreateEditDto dto){
        return deliveryListReadMapper.map(deliveryListRepository.save(deliveryListCreateEditMapper.map(dto)));
    }

    public DeliveryListReadDto read(Integer id){
        return deliveryListReadMapper.map(deliveryListRepository.findById(id).get());
    }

    public DeliveryListReadDto update(DeliveryListCreateEditDto dto, Integer id){
        DeliveryList deliveryList = deliveryListRepository.findById(id).get();
        return deliveryListReadMapper.map(deliveryListCreateEditMapper.map(dto, deliveryList));
    }

    public void delete(Integer id){
        deliveryListRepository.delete(deliveryListRepository.findById(id).get());
    }
}
