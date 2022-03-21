package com.example.maktabcha.service;

import com.example.maktabcha.dto.ApiResponse;
import com.example.maktabcha.dto.ClassDTO;
import com.example.maktabcha.entity.Book;
import com.example.maktabcha.entity.Classes;
import com.example.maktabcha.repository.BookRepository;
import com.example.maktabcha.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {
    @Autowired
    ClassRepository classRepository;
    @Autowired
    BookRepository bookRepository;

    public ApiResponse getAll() {
        List<Classes> classes = classRepository.findAll();
        return new ApiResponse("etaginga siqqanicha ol!", true, classes);
    }

    public ApiResponse getOne(Integer id) {
        Optional<Classes> optionalClasses = classRepository.findById(id);
        if (optionalClasses.isPresent()) {
            return new ApiResponse("mana", true, optionalClasses.get());
        }
        return new ApiResponse("yuq", false, null);
    }

    public ApiResponse save(ClassDTO dto) {
        Classes classes = new Classes();
        classes.setName(dto.getName());
//        List<Book> bookRepositoryAllById = bookRepository.findAllById(dto.getBooksId());
//        classes.setBookList(bookRepositoryAllById);
        Classes saveClass = classRepository.save(classes);
        return new ApiResponse("Ok", true, saveClass);
    }

    public ApiResponse edit(Integer id, ClassDTO dto) {
        Optional<Classes> optionalClasses = classRepository.findById(id);
        if (optionalClasses.isPresent()) {
            Classes classs = optionalClasses.get();
            classs.setName(dto.getName());
//            List<Book> bookRepositoryAllById = bookRepository.findAllById(dto.getBooksId());
//            classs.setBookList(bookRepositoryAllById);
            Classes saveClass = classRepository.save(classs);
            return new ApiResponse("bo`ldi", true, saveClass);
        }
      return   new ApiResponse("Bunday sinf topilmadi",false,null);
    }

    public ApiResponse delete(Integer id) {
        Optional<Classes> optionalClasses = classRepository.findById(id);
        if (optionalClasses.isPresent()){
            classRepository.deleteById(id);
            return new ApiResponse("ketdi",true,optionalClasses.get());
        }
        return new ApiResponse("bunday sinf topilmadi",false,null);
    }
}
