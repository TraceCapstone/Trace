package com.trace.trace.repositories;

import com.trace.trace.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query(value = "SELECT stage, created_at FROM stages s JOIN applications_stages ast ON ast.stage_id = s.id JOIN applications a ON ast.application_id = a.id WHERE a.id = ?1 ORDER BY ast.created_at DESC LIMIT 1", nativeQuery = true)
    Stage findMostRecentStageForApplication(long applicationId);

    Application findByPoc(PointOfContact poc);
    List<Application> findAllByUser(User user);

    @Modifying
    @Transactional
    @Query(value = "UPDATE applications a SET a.resume_id = null WHERE a.resume_id = :resumeId", nativeQuery = true)
    void update(@Param("resumeId") long resumeId);
}
