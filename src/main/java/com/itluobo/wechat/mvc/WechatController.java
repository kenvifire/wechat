package com.itluobo.wechat.mvc;

import org.apache.log4j.Logger;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.MarshalledObject;
import java.util.Date;

@Controller
public class WechatController {
	private static final Logger logger = Logger.getLogger(WechatController.class);


	@RequestMapping("/service/message")
	public ModelAndView printWelcome(HttpServletRequest request, ModelMap model) {
		String echoStr = request.getParameter("echostr");

		if(!StringUtils.isEmpty(echoStr)){
			model.put("message",echoStr);
			return new ModelAndView("validate",model);
		}else{
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
				String line;
				while((line = br.readLine()) != null){
					logger.info(line);
				}
			}catch (IOException e){
				e.printStackTrace();
			}

			model.put("message",new Date());
			return new ModelAndView("validate",model);
		}

	}
}