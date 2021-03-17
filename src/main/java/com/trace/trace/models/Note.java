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
    private String body;

    @Column(nullable = false)
    private Date createdAt;

    @ManyToOne
    private Application applications;

    //    Constructors
    public Note() {
    }

    public Note(String body, Date createdAt, Application applications) {
        this.body = body;
        this.createdAt = createdAt;
        this.applications = applications;
    }

//    Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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
