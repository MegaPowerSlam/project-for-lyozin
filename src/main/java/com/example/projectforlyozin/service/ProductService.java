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

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductReadMapper productReadMapper;
    private final ProductCreateEditMapper productCreateEditMapper;

    public ProductReadDto create(ProductCreateEditDto dto)throws Exception{
        Product product = productCreateEditMapper.map(dto);
        if (product.getProductName().equals("") || (product.getPrice().doubleValue() <= 0.0) || product.getDescription().
                equals("") || product.getImageUrl().equals("")) throw new Exception("");
        return productReadMapper.map(productRepository.save(product));
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

    public List<ProductReadDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(entity ->
                    productReadMapper.map(entity)
                )
                .collect(Collectors.toList());
    }
}
