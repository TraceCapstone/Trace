package com.trace.trace.repositories;

import com.trace.trace.models.Note;
import com.trace.trace.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotesRepository extends JpaRepository<Note, Long> {
    public List<Note> findAllByUserId(long userId);
}
