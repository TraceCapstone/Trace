package com.trace.trace.repositories;

import com.trace.trace.models.Note;
import com.trace.trace.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface NotesRepository extends JpaRepository<Note, Long> {
}
