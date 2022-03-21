package com.example.maktabcha.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDto {
    private String deviceId;
    private List<Integer> bookIds;
}
