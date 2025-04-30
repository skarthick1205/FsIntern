package com.example.batch.writer;

import com.example.batch.model.User;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class ConsoleItemWriter implements ItemWriter<User> {

    @Override
    public void write(Chunk<? extends User> items) {
        System.out.println("Inserted records:");
        items.forEach(System.out::println);
    }
}
