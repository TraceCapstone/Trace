package com.trace.trace.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true)
    private String note;

    @Column(nullable = false)
    private Date createdAt;

    @ManyToOne
    private Application applications;

//    Constructors
    public Note() {
    }

    public Note(long id, String note, Date createdAt, Application applications) {
        this.id = id;
        this.note = note;
        this.createdAt = createdAt;
        this.applications = applications;
    }

//    Getters and Setters
    public long getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Application getApplications() {
        return applications;
    }

    public void setApplications(Application applications) {
        this.applications = applications;
    }
}
