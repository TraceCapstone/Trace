package com.trace.trace.repositories;

import com.trace.trace.models.ApplicationStage;
import com.trace.trace.models.ApplicationStageId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationStageRepository extends JpaRepository<ApplicationStage, Long> {
}
