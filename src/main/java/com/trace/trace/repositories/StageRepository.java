package com.trace.trace.repositories;

import com.trace.trace.models.ApplicationStage;
import com.trace.trace.models.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StageRepository extends JpaRepository<Stage, Long> {

}
