package com.trace.trace.repositories;

import com.trace.trace.models.Application;
import com.trace.trace.models.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query(value = "SELECT * FROM stages JOIN applications_stages ON as.stage_id = s.id JOIN applications a ON as.application_id = a.id WHERE a.id = ? ORDER BY as.created_at DESC LIMIT 1", nativeQuery = true)
    Stage findMostRecentStageForApplication(long applicationId);
}
