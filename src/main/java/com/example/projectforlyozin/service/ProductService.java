package com.example.projectforlyozin.service;

import com.example.projectforlyozin.dto.CustomerCreateEditDto;
import com.example.projectforlyozin.dto.CustomerReadDto;
import com.example.projectforlyozin.dto.ProductCreateEditDto;
import com.example.projectforlyozin.dto.ProductReadDto;
import com.example.projectforlyozin.entity.Customer;
import com.example.projectforlyozin.entity.Product;
import com.example.projectforlyozin.mapper.ProductCreateEditMapper;
import com.example.projectforlyozin.mapper.ProductReadMapper;
import com.example.projectforlyozin.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductReadMapper productReadMapper;
    private final ProductCreateEditMapper productCreateEditMapper;

    public ProductReadDto create(ProductCreateEditDto dto){
        return productReadMapper.map(productRepository.save(productCreateEditMapper.map(dto)));
    }

    public ProductReadDto read(Integer id){
        return productReadMapper.map(productRepository.findById(id).get());
    }

    public ProductReadDto update(ProductCreateEditDto dto, Integer id){
        Product product = productRepository.findById(id).get();
        return productReadMapper.map(productCreateEditMapper.map(dto, product));
    }

    public void delete(Integer id){
        productRepository.delete(productRepository.findById(id).get());
    }

}
