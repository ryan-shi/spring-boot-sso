package com.ryan.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryan.domain.User;
import com.ryan.repository.UserRepository;

@RestController
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
    private UserRepository userRepository;
	
	@RequestMapping("/user")
    public User user(Principal user_noinfo) {
        User user = userRepository.findByUsername(user_noinfo.getName());
        log.info("user: {}",user);
        log.info("department: {}",user.getDepartment());
        return user;
    }

}
