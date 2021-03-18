package com.trace.trace.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="stages")
public class Stage implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true)
    private String stage;

    @OneToMany(mappedBy = "stage")
    private List<ApplicationStage> application;

    //CONSTRUCTORS
    public Stage() {
    }

    public Stage(long id, String stage, List<ApplicationStage> application) {
        this.id = id;
        this.stage = stage;
        this.application = application;
    }

    //GETTERS AND SETTERS
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public List<ApplicationStage> getApplication() {
        return application;
    }

    public void setApplication(List<ApplicationStage> application) {
        this.application = application;
    }

}
