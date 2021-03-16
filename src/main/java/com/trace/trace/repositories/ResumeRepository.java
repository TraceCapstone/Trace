package com.trace.trace.repositories;

import com.trace.trace.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    public List<Resume> findAllByUserId(long userId);
}
