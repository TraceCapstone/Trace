package com.trace.trace;



import com.trace.trace.models.*;
import com.trace.trace.repositories.ApplicationRepository;
import com.trace.trace.repositories.UserRepository;
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

    public StartupRunner(UserRepository userDao, PasswordEncoder passwordEncoder, ApplicationRepository applicationDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.applicationDao = applicationDao;
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

//        List<Application> appList = new ArrayList<>();
//        appList.add(new Application("Oracle", "Java Engineer", "Lorem ipsum dolor sit amet, consectetur adipisicing elit.", "San Antonio", 300000, "Full Time", new Date(System.currentTimeMillis()), new Date(19938467798678678L), "", "", "", user, new Resume("resume1", new Date(System.currentTimeMillis()), "", "", user, appList), Arrays.asList(new Note("lorem ipsum", new Date(System.currentTimeMillis()), appList.get(0)), Arrays.asList(new PointOfContact()))));
//        for (Application app : appList) {@localhost
//            applicationDao.save(app);
//        }




    }
}
