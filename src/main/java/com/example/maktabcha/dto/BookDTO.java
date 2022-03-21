package com.example.maktabcha.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDTO {
    @NotNull(message = "Okay😊")
    private String name;

    @NotNull
    private String description;

    @NotNull
    private List<String> authors;

    @NotNull
    private Integer classId;
}
