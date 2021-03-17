package com.trace.trace.repositories;

import com.trace.trace.models.ApplicationStage;
import com.trace.trace.models.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface StageRepository extends JpaRepository<Stage, Long> {
}
