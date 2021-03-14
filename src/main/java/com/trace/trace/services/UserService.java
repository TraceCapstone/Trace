package com.trace.trace.services;

import com.trace.trace.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserService {
    public User loggedInUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
