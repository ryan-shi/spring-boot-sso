package com.ryan.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteErrorController implements ErrorController {

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
}
