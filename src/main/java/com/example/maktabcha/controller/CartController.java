package com.example.maktabcha.controller;

import com.example.maktabcha.dto.ApiResponse;
import com.example.maktabcha.dto.CartDto;
import com.example.maktabcha.entity.Cart;
import com.example.maktabcha.repository.CartRepository;
import com.example.maktabcha.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/api/cart")
@RestController
@RequiredArgsConstructor
public class CartController {
    final CartRepository cartRepository;
    final CartService cartService;

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable UUID id){
        Optional<Cart> byId = cartRepository.findById(id);
        return ResponseEntity.ok().body(byId.get());
    }
    @GetMapping("/list")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok().body(cartRepository.findAll());
    }
    @PostMapping("/add")
    public HttpEntity<?> add(@Valid @RequestBody CartDto dto){
        ApiResponse apiResponse=cartService.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@PathVariable UUID id,@Valid @RequestBody CartDto dto){
        ApiResponse apiResponse=cartService.edit(id,dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable UUID id){
        ApiResponse apiResponse=cartService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?201:404).body(apiResponse);
    }


}
