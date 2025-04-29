package com.example.jpaquery;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 0. Add User (Save new user)
    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    // 1. Native Query
    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmailNative(email);
    }

    // 2. JPQL Query
    @GetMapping("/name/{name}")
    public List<User> getUsersByName(@PathVariable String name) {
        return userService.getUsersByNameJPQL(name);
    }

    // 3. Derived Query
    @GetMapping("/search/email")
    public List<User> searchByEmailKeyword(@RequestParam String keyword) {
        return userService.searchEmailContaining(keyword);
    }

    // 4. Pagination Query
    @GetMapping("/paginated")
    public List<User> getUsersPaginated(@RequestParam int page, @RequestParam int size) {
        return userService.getAllUsersPaginated(page, size);
    }

    // 5. Sorting Query
    @GetMapping("/sorted")
    public List<User> getAllSortedByName() {
        return userService.getAllUsersSortedByName();
    }

    // 6. Update Query
    @PutMapping("/updateName")
    public String updateUserName(@RequestParam Long id, @RequestParam String name) {
        int updated = userService.updateUserName(id, name);
        return updated + " record(s) updated.";
    }

    // 7. Delete Query
    @DeleteMapping("/deleteByEmail")
    public String deleteUserByEmail(@RequestParam String email) {
        int deleted = userService.deleteUserByEmail(email);
        return deleted + " record(s) deleted.";
    }

    //criteria api building used to build the query dynamically
    // here we can get the details of the user based on either name or email or using both at a time
    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam(required = false) String email,
                                  @RequestParam(required = false) String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);

        List<Predicate> predicates = new ArrayList<>();

        if (email != null) {
            predicates.add(cb.equal(root.get("email"), email));
        }
        if (name != null) {
            predicates.add(cb.equal(root.get("name"), name));
        }

        query.select(root).where(cb.and(predicates.toArray(new Predicate[0])));
        return entityManager.createQuery(query).getResultList();
    }

}

