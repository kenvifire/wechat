package com.itluobo.wechat.mvc;

import com.itluobo.wechat.domain.UserMessage;
import com.itluobo.wechat.service.WechatService;
import com.itluobo.wechat.uitls.IOUtils;
import com.itluobo.wechat.uitls.MessageUtils;
import com.thoughtworks.xstream.XStream;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.rmi.MarshalledObject;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
			return new ModelAndView("validate",model);

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

				OutputStream out =  response.getOutputStream();
				IOUtils.writeMsg(userMessage,out);

				out.close();

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
			return new ModelAndView("validate",model);
		}

	}

}