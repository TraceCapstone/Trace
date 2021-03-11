package com.trace.trace.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="applications_stages")
public class ApplicationStage implements Serializable {

    @Column(nullable = false)
    private Date createdAt;

    @Id
    @ManyToOne
    @JoinColumn(name = "application_id", referencedColumnName = "id")
    private Application application;

    @Id
    @ManyToOne
    @JoinColumn(name = "stage_id", referencedColumnName = "id")
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
