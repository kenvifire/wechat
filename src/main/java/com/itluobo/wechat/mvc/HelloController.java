package com.itluobo.wechat.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	@RequestMapping("/greeting")
	public String printWelcome(@RequestParam("echostr")String echostr, ModelMap model) {
		model.addAttribute("message", echostr);
		return "hello";
	}
}