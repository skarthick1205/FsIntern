package com.example.DateFormatConcatenation.repository;


import com.example.DateFormatConcatenation.model.LoanEmiSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanEmiRepository extends JpaRepository<LoanEmiSchedule, Long> {
}
