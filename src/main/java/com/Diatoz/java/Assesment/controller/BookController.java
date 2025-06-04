package com.Diatoz.java.Assesment.controller;

import com.Diatoz.java.Assesment.dto.BookDTO;
import com.Diatoz.java.Assesment.service.BookService;
import com.Diatoz.java.Assesment.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BorrowService borrowService;

    // View all available books
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MEMBER')")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    // Add new book (ADMIN only)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {
        BookDTO created = bookService.addBook(bookDTO);
        return ResponseEntity.ok(created);
    }

    // Update existing book (ADMIN only)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        BookDTO updated = bookService.updateBook(id, bookDTO);
        return ResponseEntity.ok(updated);
    }

    // Delete a book (ADMIN only)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    // Member borrows a book
    @PostMapping("/borrow/{bookId}")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<String> borrowBook(@PathVariable Long bookId) {
        borrowService.borrowBook(bookId);
        return ResponseEntity.ok("Book borrowed successfully.");
    }

    // Member returns a book
    @PostMapping("/return/{bookId}")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId) {
        borrowService.returnBook(bookId);
        return ResponseEntity.ok("Book returned successfully.");
    }
}
