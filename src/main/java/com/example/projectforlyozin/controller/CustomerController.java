package com.example.projectforlyozin.controller;

import com.example.projectforlyozin.dto.CustomerCreateEditDto;
import com.example.projectforlyozin.dto.CustomerReadDto;
import com.example.projectforlyozin.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/customer")
@RestController
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerReadDto> create(@RequestBody CustomerCreateEditDto dto){
        CustomerReadDto customerReadDto = customerService.create(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<CustomerReadDto> read(@PathVariable Integer id){
//        return new ResponseEntity<>(customerService.read(id), HttpStatus.OK);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<CustomerReadDto> update(@RequestBody CustomerCreateEditDto dto,@PathVariable Integer id){
//        return new ResponseEntity<>(customerService.update(dto, id), HttpStatus.ACCEPTED);
//    }
//
//    @DeleteMapping("/{id}")
//    public HttpStatus delete(@PathVariable Integer id){
//        customerService.delete(id);
//        return HttpStatus.I_AM_A_TEAPOT;
//    }

}
