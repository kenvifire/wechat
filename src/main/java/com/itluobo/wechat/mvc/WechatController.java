package com.itluobo.wechat.mvc;

import com.itluobo.wechat.domain.UserMessage;
import com.itluobo.wechat.service.WechatService;
import com.itluobo.wechat.uitls.MessageUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@Controller
public class WechatController {

	private static final Logger logger = Logger.getLogger(WechatController.class);

	@Autowired
	private WechatService wechatService;


	@RequestMapping("/service/message")
	public ModelAndView printWelcome(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String echoStr = request.getParameter("echostr");

		if(!StringUtils.isEmpty(echoStr)){

			model.put("message",echoStr);
			return new ModelAndView("message",model);

		}else{
			BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(request.getInputStream()));

				StringBuilder stringBuilder = new StringBuilder();

				String line;
				while((line = br.readLine()) != null){
					stringBuilder.append(line);
				}
				br.close();
				UserMessage userMessage = MessageUtils.parseUserMsg(stringBuilder.toString());

				UserMessage retMsg = wechatService.processMsg(userMessage);

				response.setContentType("text/html;charset=UTF-8");

				return new ModelAndView("retMessage","model",retMsg);


			}catch (IOException e){
				logger.error("error when reading input stream",e);
			}finally {
				if(br != null){
					try {
						br.close();
					}catch (IOException e){
						//do nothing here
					}
				}
			}


			model.put("message",new Date());
			return new ModelAndView("message",model);
		}

	}

}