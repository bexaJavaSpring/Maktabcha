package com.example.maktabcha.repository;

import com.example.maktabcha.entity.Book;
import com.example.maktabcha.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
    Optional<Cart> findByDeviceId(String deviceId);
    boolean existsByBooks(Book book);


}
