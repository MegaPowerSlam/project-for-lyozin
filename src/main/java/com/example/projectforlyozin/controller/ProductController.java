package com.example.projectforlyozin.controller;

import com.example.projectforlyozin.dto.ProductCreateEditDto;
import com.example.projectforlyozin.dto.ProductReadDto;
import com.example.projectforlyozin.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductReadDto> create(@RequestBody ProductCreateEditDto dto){
        return new ResponseEntity<>(productService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductReadDto>> getAll(){
        List<ProductReadDto> list = productService.getAll();
        return ResponseEntity
                .ok()
                .body(list);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductReadDto> delete(@PathVariable Integer id){
        productService.delete(id);
        return ResponseEntity
                .ok()
                .build();
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<ProductReadDto> update(@RequestBody ProductCreateEditDto dto, @PathVariable Integer id){
//        return new ResponseEntity<>(productService.update(dto, id), HttpStatus.ACCEPTED);
//    }
}
