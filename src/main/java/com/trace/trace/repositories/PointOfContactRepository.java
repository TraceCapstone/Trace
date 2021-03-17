package com.trace.trace.repositories;

import com.trace.trace.models.PointOfContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface PointOfContactRepository extends JpaRepository<PointOfContact, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE PointOfContact p SET p.email = :email, p.firstName = :firstName, p.lastName = :lastName, p.phoneNumber = :phoneNumber, p.position = :position WHERE p.id = :id")
    void update(@Param("email") String email, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("phoneNumber") String phoneNumber, @Param("position") String position, @Param("id") long id);
}
