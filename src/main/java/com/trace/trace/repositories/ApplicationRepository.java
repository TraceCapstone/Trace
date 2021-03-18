package com.trace.trace.repositories;

import com.trace.trace.models.Application;
import com.trace.trace.models.Note;
import com.trace.trace.models.PointOfContact;
import com.trace.trace.models.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query(value = "SELECT stage, created_at FROM stages s JOIN applications_stages ast ON ast.stage_id = s.id JOIN applications a ON ast.application_id = a.id WHERE a.id = ?1 ORDER BY ast.created_at DESC LIMIT 1", nativeQuery = true)
    Stage findMostRecentStageForApplication(long applicationId);

    Application findByPoc(PointOfContact poc);
}
