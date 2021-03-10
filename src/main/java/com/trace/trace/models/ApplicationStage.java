package com.trace.trace.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="applications_stages")
public class ApplicationStage {

    @Column(nullable = false)
    private Date createdAt;

    @ManyToOne
    private Application application;

    @ManyToOne
    private Stage stage;

//    Constructors

    public ApplicationStage() {
    }

    public ApplicationStage(Date createdAt, Application application, Stage stage) {
        this.createdAt = createdAt;
        this.application = application;
        this.stage = stage;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
