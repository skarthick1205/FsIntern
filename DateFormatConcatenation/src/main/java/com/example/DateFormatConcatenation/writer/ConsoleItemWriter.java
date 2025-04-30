package com.example.DateFormatConcatenation.writer;

import com.example.DateFormatConcatenation.model.LoanEmiSchedule;
import com.example.DateFormatConcatenation.repository.LoanEmiRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsoleItemWriter implements ItemWriter<List<LoanEmiSchedule>> {

    @Autowired
    private LoanEmiRepository repository;

    @Override
    public void write(Chunk<? extends List<LoanEmiSchedule>> items) {
        for (List<LoanEmiSchedule> scheduleList : items) {
            repository.saveAll(scheduleList);
            scheduleList.forEach(System.out::println); // Output to console
        }
    }
}
