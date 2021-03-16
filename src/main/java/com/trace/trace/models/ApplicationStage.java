package com.trace.trace.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="applications_stages")
//@IdClass(ApplicationStageId.class)
public class ApplicationStage implements Serializable {

    @Id
    private long id;

    @Column(nullable = false)
    private Date createdAt;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", referencedColumnName = "id")
    private Application application;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id", referencedColumnName = "id")
    private Stage stage;



    //CONSTRUCTORS
    public ApplicationStage() {
    }

    public ApplicationStage(Date createdAt, Application application, Stage stage) {
        this.createdAt = createdAt;
        this.application = application;
        this.stage = stage;
    }

    //GETTERS AND SETTERS


    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    //    public ApplicationStageId getId() {
//        return new ApplicationStageId(application, stage);
//    }
//
//    public void setId(ApplicationStageId id) {
//        this.application = id.getApplication();
//        this.stage = id.getStage();
//    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
