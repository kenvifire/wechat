package com.itluobo.wechat.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WechatController {

	@RequestMapping("/service/message")
	public ModelAndView printWelcome(@RequestParam("echostr")String echostr, ModelMap model) {
		model.addAttribute("message", echostr);

		return new ModelAndView("validate",model);
	}
}