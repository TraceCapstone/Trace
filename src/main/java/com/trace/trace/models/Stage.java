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

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Stage stage = (Stage) o;
        return Objects.equals( id, stage.id );
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


//    Constructors
    public Stage() {
    }

    public Stage(long id, String stage, List<ApplicationStage> application) {
        this.id = id;
        this.stage = stage;
        this.application = application;
    }

//    Getters and Setters
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
