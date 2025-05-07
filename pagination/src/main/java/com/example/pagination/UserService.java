package com.example.pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> getUsers(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    public long getTotalCount() {
        return userRepository.count();
    }
}
