package com.example.maktabcha.controller;

import com.example.maktabcha.dto.ApiResponse;
import com.example.maktabcha.dto.BookDTO;
import com.example.maktabcha.repository.BookRepository;
import com.example.maktabcha.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    final BookService bookService;
     final BookRepository bookRepository;

    @GetMapping("/start")
    public HttpEntity<?> start(@RequestParam("deviceId") String deviceId) {
        //birinchi ilova ishga tushganida device idni olib cart yasash kerak
        ApiResponse response = bookService.addCart(deviceId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/list")
    public HttpEntity<?> getAll() {
        return ResponseEntity.ok().body(bookRepository.findAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        ApiResponse all = bookService.getOne(id);
        return ResponseEntity.status(all.isSuccess() ? HttpStatus.FOUND : HttpStatus.NOT_FOUND).body(all);
    }

    @PostMapping("/add")
    public HttpEntity<?> save(@Valid @RequestBody BookDTO dto) {
        ApiResponse all = bookService.save(dto);
        return ResponseEntity.status(all.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(all);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @Valid @RequestBody BookDTO dto) {
        ApiResponse all = bookService.edit(id, dto);
        return ResponseEntity.status(all.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(all);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse all = bookService.delete(id);
        return ResponseEntity.status(all.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(all);
    }

    @GetMapping("/search")
    public HttpEntity<?> search(@RequestParam(defaultValue = "0", name = "classId", required = false) Integer classId,
                                @RequestParam(defaultValue = "0", name = "langId", required = false) Integer langId) {
        ApiResponse response = bookService.searchBy(classId, langId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/saved")
    public HttpEntity<?> saved(@RequestParam String deviceId, @RequestParam Integer bookId) {
        ApiResponse response = bookService.saved(deviceId, bookId);
        return ResponseEntity.ok().body(response);
    }
}
