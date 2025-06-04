package com.Diatoz.java.Assesment.controller;

import com.Diatoz.java.Assesment.entity.BorrowRecord;
import com.Diatoz.java.Assesment.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class BorrowHistoryController {

    @Autowired
    private BorrowService borrowService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<BorrowRecord> getAllBorrowHistory() {
        return borrowService.getAllBorrowRecords();
    }
}
