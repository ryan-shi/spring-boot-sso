package com.ryan.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteErrorController implements ErrorController {

	
	private static final Logger log = LoggerFactory.getLogger(SiteErrorController.class);

	
	@RequestMapping(value="/error")
    public String handleError(){
        return "403";
    }
	
	@Override
	public String getErrorPath() {
		return "403";
	}

	@RequestMapping(value="/deny")
    public String deny(){
        return "deny";
    }
	
	@RequestMapping("/tosignout")
    public String tosso() {
        return "tosignout";
    }

    @RequestMapping("/login")
    public String login() {
    	log.info("come here!");
        return "redirect:/#/";
    }

    @RequestMapping("/")
    public String index(Model model, Principal user) throws Exception{
        model.addAttribute("user", user);
        return "home";
    }
}
