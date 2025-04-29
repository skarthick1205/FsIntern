package com.example.jpaquery;


import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.domain.Pageable;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    // 1. Native Query (Real SQL)
    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    User findByEmailNative(@Param("email") String email);

    // 2. JPQL Query (Entity based, not real SQL)
    @Query("SELECT u FROM User u WHERE u.name = :name")
    List<User> findByNameJPQL(@Param("name") String name);

    // 3. Derived Query (Spring Data Magic, no @Query needed)
    List<User> findByEmailContaining(String keyword);
    // will search email containing "keyword" like %keyword%

    // 4. Pagination Support (for large datasets)
    @Query("SELECT u FROM User u")
    List<User> findAllUsers(Pageable pageable);

    // 5. Sorting Support
    List<User> findAllByOrderByNameAsc();  // Sort by name ascending

    // 6. Update Query (JPQL Update)
    @Modifying
    @Query("UPDATE User u SET u.name = :name WHERE u.id = :id")
    int updateUserNameById(@Param("id") Long id, @Param("name") String name);

    // 7. Delete Query (JPQL Delete)
    @Modifying
    @Query("DELETE FROM User u WHERE u.email = :email")
    int deleteByEmail(@Param("email") String email);
}

