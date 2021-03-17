package com.trace.trace.repositories;

import com.trace.trace.models.ApplicationStage;
import com.trace.trace.models.ApplicationStageId;
import com.trace.trace.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ApplicationStageRepository extends JpaRepository<ApplicationStage, ApplicationStageId> {
//    public Date getCreatedAt();
}
