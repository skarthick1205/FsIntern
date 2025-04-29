package com.example.jpaquery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User getUserByEmailNative(String email) {
        return userRepository.findByEmailNative(email);
    }

    public List<User> getUsersByNameJPQL(String name) {
        return userRepository.findByNameJPQL(name);
    }

    public List<User> searchEmailContaining(String keyword) {
        return userRepository.findByEmailContaining(keyword);
    }

    public List<User> getAllUsersPaginated(int page, int size) {
        return userRepository.findAllUsers(PageRequest.of(page, size));
    }

    public List<User> getAllUsersSortedByName() {
        return userRepository.findAllByOrderByNameAsc();
    }

    @Transactional
    public int updateUserName(Long id, String name) {
        return userRepository.updateUserNameById(id, name);
    }

    @Transactional
    public int deleteUserByEmail(String email) {
        return userRepository.deleteByEmail(email);
    }
    public User addUser(User user) {
        //user.setId(null); // Always set id to null for new adds
        return userRepository.save(user);
    }


}
