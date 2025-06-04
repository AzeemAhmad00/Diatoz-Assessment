package com.Diatoz.java.Assesment.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.Diatoz.java.Assesment.entity.Book;
import com.Diatoz.java.Assesment.entity.BorrowRecord;
import com.Diatoz.java.Assesment.entity.User;
import com.Diatoz.java.Assesment.repository.BookRepository;
import com.Diatoz.java.Assesment.repository.BorrowRecordRepository;
import com.Diatoz.java.Assesment.repository.UserRepository;

@Service
public class BorrowService {

    @Autowired private BorrowRecordRepository borrowRecordRepository;
    @Autowired private BookRepository bookRepository;
    @Autowired private UserRepository userRepository;

    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElseThrow();
    }

    public void borrowBook(Long bookId) {
        User user = getCurrentUser();
        Book book = bookRepository.findById(bookId).orElseThrow();

        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Book not available");
        }

        boolean alreadyBorrowed = borrowRecordRepository
            .findByUserAndBookAndReturnDateIsNull(user, book).isPresent();

        if (alreadyBorrowed) {
            throw new RuntimeException("You already borrowed this book and haven't returned it yet.");
        }

        BorrowRecord record = new BorrowRecord();
        record.setUser(user);
        record.setBook(book);
        record.setBorrowDate(LocalDate.now());
        borrowRecordRepository.save(record);

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
    }

    public void returnBook(Long bookId) {
        User user = getCurrentUser();
        Book book = bookRepository.findById(bookId).orElseThrow();

        BorrowRecord record = borrowRecordRepository
            .findByUserAndBookAndReturnDateIsNull(user, book)
            .orElseThrow(() -> new RuntimeException("You haven't borrowed this book."));

        record.setReturnDate(LocalDate.now());
        borrowRecordRepository.save(record);

        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);
    }

    public List<BorrowRecord> getUserBorrowHistory() {
        return borrowRecordRepository.findByUser(getCurrentUser());
    }

    public List<BorrowRecord> getAllBorrowRecords() {
        return borrowRecordRepository.findAll();
    }
}

