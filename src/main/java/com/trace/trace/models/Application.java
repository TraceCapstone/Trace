package com.trace.trace.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="applications")
public class Application implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true, length = 1500)
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = true)
    private Integer salary;

    @Column(nullable = true)
    private String positionType;

    @Column(nullable = false)
    private Date dateCreated;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date dateApplied;

    @Column(nullable = true)
    private String postingId;

    @Column(nullable = true)
    private String referredBy;

    @Column(nullable = true)
    private String url;

    public Application(List<ApplicationStage> applicationStage) {
        this.applicationStage = applicationStage;
    }

    @ManyToOne
    private User user;

    @ManyToOne
    private Resume resume;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
    @Column(nullable = true)
    private List<PointOfContact> poc;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
    @Column(nullable = true)
    private List<ApplicationStage> applicationStage;

    @OneToMany(mappedBy = "applications", cascade = CascadeType.ALL)
    @Column(nullable = true)
    private List<Note> note;

//    Constructors
    public Application() {
    }

    public Application(String company, String title, String description, String location, int salary, String positionType, Date dateCreated, Date dateApplied, String postingId, String referredBy, String url, User user, Resume resume, List<PointOfContact> poc, List<ApplicationStage> applicationStage, List<Note> note) {
        this.company = company;
        this.title = title;
        this.description = description;
        this.location = location;
        this.salary = salary;
        this.positionType = positionType;
        this.dateCreated = dateCreated;
        this.dateApplied = dateApplied;
        this.postingId = postingId;
        this.referredBy = referredBy;
        this.url = url;
        this.user = user;
        this.resume = resume;
        this.poc = poc;
        this.applicationStage = applicationStage;
        this.note = note;
    }



//    Getters and Setters


    public List<ApplicationStage> getApplicationStage() {
        return applicationStage;
    }

    public void setApplicationStage(List<ApplicationStage> applicationStage) {
        this.applicationStage = applicationStage;
    }

    public long getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(Date dateApplied) {
        this.dateApplied = dateApplied;
    }

    public String getPostingId() {
        return postingId;
    }

    public void setPostingId(String postingId) {
        this.postingId = postingId;
    }

    public String getReferredBy() {
        return referredBy;
    }

    public void setReferredBy(String referredBy) {
        this.referredBy = referredBy;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public List<Note> getNote() {
        return note;
    }

    public void setNote(List<Note> note) {
        this.note = note;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<PointOfContact> getPoc() {
        return poc;
    }

    public void setPoc(List<PointOfContact> poc) {
        this.poc = poc;
    }

}
