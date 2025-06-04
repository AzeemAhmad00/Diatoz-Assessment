package com.Diatoz.java.Assesment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Diatoz.java.Assesment.dto.BookDTO;
import com.Diatoz.java.Assesment.entity.Book;
import com.Diatoz.java.Assesment.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public BookDTO addBook(BookDTO dto) {
        Book book = toEntity(dto);
        Book saved = bookRepository.save(book);
        return toDTO(saved);
    }

    public BookDTO updateBook(Long id, BookDTO dto) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setCategory(dto.getCategory());
        book.setAvailableCopies(dto.getAvailableCopies());
        Book updated = bookRepository.save(book);
        return toDTO(updated);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    // Mapping helpers
    private BookDTO toDTO(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getAvailableCopies()
        );
    }

    private Book toEntity(BookDTO dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setCategory(dto.getCategory());
        book.setAvailableCopies(dto.getAvailableCopies());
        return book;
    }
}
