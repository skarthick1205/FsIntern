package com.example.batch.writer;

import com.example.batch.model.User;
import com.example.batch.repository.UserRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserItemWriter implements ItemWriter<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void write(Chunk<? extends User> items) {
        userRepository.saveAll(items.getItems());
    }
}
