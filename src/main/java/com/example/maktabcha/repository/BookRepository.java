package com.example.maktabcha.repository;

import com.example.maktabcha.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByLanguage_Id(Integer langId);

    List<Book> findAllByClasses_Id(Integer classId);

    List<Book> findAllByClasses_IdAndLanguage_Id(Integer classId, Integer langId);

}
