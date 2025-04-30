package com.example.batch.processor;
import com.example.batch.model.User;
import org.springframework.batch.item.ItemProcessor;
import java.time.LocalDate;
public class UserItemProcessor implements ItemProcessor<User, User> {
    @Override
    public User process(User user) {
        LocalDate fullDate = LocalDate.of(user.getYear(), user.getMonth(), user.getDate());
        user.setFullDate(fullDate);
        return user;
    }
}