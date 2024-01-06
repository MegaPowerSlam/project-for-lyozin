package com.example.projectforlyozin.controller;

import com.example.projectforlyozin.dto.ProductCreateEditDto;
import com.example.projectforlyozin.dto.ProductReadDto;
import com.example.projectforlyozin.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/product")
@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductReadDto> create(@RequestBody ProductCreateEditDto dto){
        return new ResponseEntity<>(productService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductReadDto> read(@PathVariable Integer id){
        return new ResponseEntity<>(productService.read(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductReadDto> update(@RequestBody ProductCreateEditDto dto, @PathVariable Integer id){
        return new ResponseEntity<>(productService.update(dto, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Integer id){
        productService.delete(id);
        return HttpStatus.I_AM_A_TEAPOT;
    }
}
