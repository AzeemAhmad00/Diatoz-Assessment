package com.Diatoz.java.Assesment.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Diatoz.java.Assesment.entity.Book;
import com.Diatoz.java.Assesment.entity.BorrowRecord;
import com.Diatoz.java.Assesment.entity.User;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    Optional<BorrowRecord> findByUserAndBookAndReturnDateIsNull(User user, Book book);
    List<BorrowRecord> findByUser(User user);
}

