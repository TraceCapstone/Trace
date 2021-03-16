package com.trace.trace.models;

import java.io.Serializable;

public class ApplicationStageId implements Serializable{

    private Application application;
    private Stage stage;

    public ApplicationStageId() {
    }

    public ApplicationStageId(Application application, Stage stage) {
        this.application = application;
        this.stage = stage;
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
