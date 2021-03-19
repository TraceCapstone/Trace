package com.trace.trace;



import com.trace.trace.models.*;
import com.trace.trace.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class StartupRunner implements CommandLineRunner {

    private final UserRepository userDao;
    private final ApplicationRepository applicationDao;
    private final PasswordEncoder passwordEncoder;
    private final StageRepository stageDao;
    private final PointOfContactRepository pocDao;
    private final ApplicationStageRepository applicationsStageDao;

    public StartupRunner(UserRepository userDao, ApplicationStageRepository applicationsStageDao, PasswordEncoder passwordEncoder, ApplicationRepository applicationDao, StageRepository stageDao, PointOfContactRepository pocDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.applicationDao = applicationDao;
        this.stageDao = stageDao;
        this.pocDao = pocDao;
        this.applicationsStageDao = applicationsStageDao;
    }

    @Override
    public void run(String... args) throws Exception {

        if (userDao.count() != 0) {
            return;
        }
        User user = new User();
        user.setFirstName("cody");
        user.setLastName("duck");
        user.setUsername("cody");
        user.setEmail("cody@codeup.com");
        user.setPassword(passwordEncoder.encode("codeuprocks"));
        userDao.save(user);
        Stage stage = new Stage();
        Stage stage1 = new Stage();
        Stage stage2 = new Stage();
        Stage stage3 = new Stage();

        stage.setStage("Applied");
        stage1.setStage("Interview Scheduled'");
        stage2.setStage("Interviewed");
        stage3.setStage("Technical Interview Scheduled");

        stageDao.save(stage);
        stageDao.save(stage1);
        stageDao.save(stage2);
        stageDao.save(stage3);


        Application app = new Application();
        app.setUser(user);
        app.setDateApplied(new Date(1614977184000L));
        app.setDateCreated(new Date(System.currentTimeMillis()));
        app.setCompany("pineapple");
        app.setLocation("San Antonio");
        app.setSalary(200399);
        app.setPositionType("full time");
        app.setTitle("orange");

        PointOfContact poc = new PointOfContact();
        poc.setApplication(app);
        poc.setEmail("name@email.com");
        poc.setPhoneNumber("29934881");
        poc.setFirstName("Ryan");
        poc.setLastName("Mcguire");
        poc.setPosition("big important person");

        applicationDao.save(app);
        pocDao.save(poc);

        ApplicationStage as = new ApplicationStage();
        as.setCreatedAt(new Date(System.currentTimeMillis()));
        as.setApplication(app);
        as.setStage(stage3);
        applicationsStageDao.save(as);

        ApplicationStage as2 = new ApplicationStage();
        as.setCreatedAt(new Date(1614977184000L));
        as.setApplication(app);
        as.setStage(stage1);
        applicationsStageDao.save(as);

//        List<Application> appList = new ArrayList<>();
//        appList.add(new Application("Oracle", "Java Engineer", "Lorem ipsum dolor sit amet, consectetur adipisicing elit.", "San Antonio", 300000, "Full Time", new Date(System.currentTimeMillis()), new Date(19938467798678678L), "", "", "", user, new Resume("resume1", new Date(System.currentTimeMillis()), "", "", user, appList), Arrays.asList(new Note("lorem ipsum", new Date(System.currentTimeMillis()), appList.get(0)), Arrays.asList(new PointOfContact()))));
//        for (Application app : appList) {@localhost
//            applicationDao.save(app);
//        }




    }
}
