package com.trace.trace.models;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="resumes")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true)
    private String title;

    @Column(nullable = true)
    private Date createdAt;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    @Value("${file-upload-path}")
    private String filePath;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy="resume")
    private List<Application> applications;

//    Constructors

    public Resume() {
    }

    public Resume(long id, String title, Date date, String description, String filePath, User user, List<Application> applications) {
        this.id = id;
        this.title = title;
        this.createdAt = date;
        this.description = description;
        this.filePath = filePath;
        this.user = user;
        this.applications = applications;
    }

//    Getters and Setters

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date date) {
        this.createdAt = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }
}
