package com.Diatoz.java.Assesment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Diatoz.java.Assesment.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Add custom queries if needed
}
