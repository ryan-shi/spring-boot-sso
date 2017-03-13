package com.ryan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ryan.domain.User;
import com.ryan.repository.UserRepository;

import java.security.Principal;

@Controller
public class TestController {

	@Autowired
	UserRepository userRepository;
	
    @RequestMapping("/")
    public String getUser(Model model, Principal user){
        model.addAttribute("user",user);
        return "user/index";
    }

    @RequestMapping("/show")
    public String show(Model model,Long id) throws Exception{
    	User user=userRepository.findOne(id);
        model.addAttribute("user",user);
        return "user/show";
    }

}
