package com.hejinyo.controller.other.ipController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelpController {
	@RequestMapping(value = "help")
	public String help(){
		
		return "/page/help";
	}
}
