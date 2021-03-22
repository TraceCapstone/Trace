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
        Stage stage4 = new Stage();
        Stage stage5 = new Stage();
        Stage stage6 = new Stage();
        Stage stage7 = new Stage();

        stage.setStage("Applied");
        stage1.setStage("General Interview");
        stage2.setStage("Technical Interview");
        stage3.setStage("Behavioral Interview");
        stage4.setStage("Follow-Up Interview");
        stage5.setStage("Offer Extended");
        stage6.setStage("Offer Accepted");
        stage7.setStage("Offer Declined");


        stageDao.save(stage);
        stageDao.save(stage1);
        stageDao.save(stage2);
        stageDao.save(stage3);
        stageDao.save(stage4);
        stageDao.save(stage5);
        stageDao.save(stage6);
        stageDao.save(stage7);


        Application app = new Application();
        app.setUser(user);
        app.setDateApplied(new Date(1614977184000L));
        app.setDateCreated(new Date(System.currentTimeMillis()));
        app.setCompany("pineapple");
        app.setLocation("5443 Beverly Dr NE, Olympia, WA 98516");
        app.setSalary(200399);
        app.setPositionType("full time");
        app.setTitle("orange");

        Application app1 = new Application();
        app1.setUser(user);
        app1.setDateApplied(new Date(1614977184000L));
        app1.setDateCreated(new Date(System.currentTimeMillis()));
        app1.setCompany("pineapple");
        app1.setLocation("5443 Beverly Dr NE, Olympia, WA 98516");
        app1.setSalary(200399);
        app1.setPositionType("full time");
        app1.setTitle("orange");

        Application app2 = new Application();
        app2.setUser(user);
        app2.setDateApplied(new Date(1614977184000L));
        app2.setDateCreated(new Date(System.currentTimeMillis()));
        app2.setCompany("pineapple");
        app2.setLocation("5443 Beverly Dr NE, Olympia, WA 98516");
        app2.setSalary(200399);
        app2.setPositionType("full time");
        app2.setTitle("orange");

        Application app3 = new Application();
        app3.setUser(user);
        app3.setDateApplied(new Date(1614977184000L));
        app3.setDateCreated(new Date(System.currentTimeMillis()));
        app3.setCompany("pineapple");
        app3.setLocation("5443 Beverly Dr NE, Olympia, WA 98516");
        app3.setSalary(200399);
        app3.setPositionType("full time");
        app3.setTitle("orange");

        Application app4 = new Application();
        app4.setUser(user);
        app4.setDateApplied(new Date(1614977184000L));
        app4.setDateCreated(new Date(System.currentTimeMillis()));
        app4.setCompany("pineapple");
        app4.setLocation("5443 Beverly Dr NE, Olympia, WA 98516");
        app4.setSalary(200399);
        app4.setPositionType("full time");
        app4.setTitle("orange");

        Application app5 = new Application();
        app5.setUser(user);
        app5.setDateApplied(new Date(1614977184000L));
        app5.setDateCreated(new Date(System.currentTimeMillis()));
        app5.setCompany("pineapple");
        app5.setLocation("5443 Beverly Dr NE, Olympia, WA 98516");
        app5.setSalary(200399);
        app5.setPositionType("full time");
        app5.setTitle("orange");

        Application app6 = new Application();
        app6.setUser(user);
        app6.setDateApplied(new Date(1614977184000L));
        app6.setDateCreated(new Date(System.currentTimeMillis()));
        app6.setCompany("pineapple");
        app6.setLocation("5443 Beverly Dr NE, Olympia, WA 98516");
        app6.setSalary(200399);
        app6.setPositionType("full time");
        app6.setTitle("orange");

        Application app7 = new Application();
        app7.setUser(user);
        app7.setDateApplied(new Date(1614977184000L));
        app7.setDateCreated(new Date(System.currentTimeMillis()));
        app7.setCompany("pineapple");
        app7.setLocation("5443 Beverly Dr NE, Olympia, WA 98516");
        app7.setSalary(200399);
        app7.setPositionType("full time");
        app7.setTitle("orange");

        PointOfContact poc = new PointOfContact();
        poc.setApplication(app);
        poc.setEmail("name@email.com");
        poc.setPhoneNumber("29934881");
        poc.setFirstName("Ryan");
        poc.setLastName("Mcguire");
        poc.setPosition("big important person");

        applicationDao.save(app);
        applicationDao.save(app1);
        applicationDao.save(app2);
        applicationDao.save(app3);
        applicationDao.save(app4);
        applicationDao.save(app5);
        applicationDao.save(app6);
        applicationDao.save(app7);
        pocDao.save(poc);

        ApplicationStage as = new ApplicationStage();
        as.setCreatedAt(new Date(System.currentTimeMillis()));
        as.setApplication(app);
        as.setStage(stage3);
        applicationsStageDao.save(as);










    }
}
