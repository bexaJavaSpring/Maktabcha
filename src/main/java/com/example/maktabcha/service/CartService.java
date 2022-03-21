package com.example.maktabcha.service;

import com.example.maktabcha.dto.ApiResponse;
import com.example.maktabcha.dto.CartDto;
import com.example.maktabcha.entity.Book;
import com.example.maktabcha.entity.Cart;
import com.example.maktabcha.repository.BookRepository;
import com.example.maktabcha.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {
   final BookRepository bookRepository;
   final CartRepository cartRepository;

    public ApiResponse add(CartDto dto) {
        Optional<Cart> byDeviceId = cartRepository.findByDeviceId(dto.getDeviceId());
        if(!byDeviceId.isPresent()){
           return new ApiResponse("This cart already exist",false);
        }

        Cart cart=new Cart();
        cart.setDeviceId(dto.getDeviceId());
        List<Book> allById = bookRepository.findAllById(dto.getBookIds());
        cart.setBooks(allById);
        Cart save = cartRepository.save(cart);
        return new ApiResponse("Added",true,save);
    }

    public ApiResponse edit(UUID id, CartDto dto) {
        Optional<Cart> byId = cartRepository.findById(id);
        List<Book> allById1 = bookRepository.findAllById(dto.getBookIds());
        if(!byId.isPresent()){
            return new ApiResponse("Xatolik",false);
        }
        if(allById1.isEmpty()){
            return new ApiResponse("Yhis cart not found",false);
        }
        Cart cart = byId.get();
        cart.setDeviceId(dto.getDeviceId());
        cart.setBooks(allById1);
        Cart save = cartRepository.save(cart);
        return new ApiResponse("Edit",true,save);
    }

    public ApiResponse delete(UUID id) {
        Optional<Cart> byId = cartRepository.findById(id);
        if(!byId.isPresent()){
            return new ApiResponse("Not found",false);
        }
        Cart cart = byId.get();
        cartRepository.delete(cart);
        return new ApiResponse("Delete",true,cart);
    }
}
