package com.example.maktabcha.controller;

import com.example.maktabcha.dto.ApiResponse;
import com.example.maktabcha.entity.Language;
import com.example.maktabcha.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/language")
@RequiredArgsConstructor
public class LanguageController {
    final LanguageService languageService;

    @GetMapping("/list")
    public HttpEntity<?> getAll() {
        ApiResponse all = languageService.getAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        ApiResponse all = languageService.getOne(id);
        return ResponseEntity.status(all.isSuccess() ? HttpStatus.FOUND : HttpStatus.NOT_FOUND).body(all);
    }

    @PostMapping("/add")
    public HttpEntity<?> save(@Valid @RequestBody Language dto) {
        ApiResponse all = languageService.save(dto);
        return ResponseEntity.status(all.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(all);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @Valid @RequestBody Language dto) {
        ApiResponse all = languageService.edit(id, dto);
        return ResponseEntity.status(all.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(all);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse all = languageService.delete(id);
        return ResponseEntity.status(all.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(all);
    }
}
