package com.trace.trace.repositories;

import com.trace.trace.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String username);

//    @Modifying
//    @Transactional
//    @Query("UPDATE User u SET u.email=:email, u.firstName=:fist_name, u.lastName=:last_name WHERE u.id=:id")
//    void update(@Param("email") String email,
//        @Param("firstName")String firstName,
//        @Param("lastName")String lastName,
//        @Param("id")Long id);
}
