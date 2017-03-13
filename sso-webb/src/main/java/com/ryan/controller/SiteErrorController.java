package com.ryan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
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
		return "/error";
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
}
