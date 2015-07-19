package com.itluobo.wechat.service.impl;

import com.google.gson.Gson;
import com.itluobo.wechat.domain.RespMsg;
import com.itluobo.wechat.service.RobotService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by kenvi on 15/7/18.
 */
@Service("robotService")
public class RobotServiceImpl implements RobotService {

    private static final Logger logger = Logger.getLogger(RobotServiceImpl.class);

    @Override
    public String getResponse(String msg) {
        try {
            String APIKEY = "FIXME";
            String INFO = URLEncoder.encode(msg, "utf-8");
            String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO;
            URL getUrl = new URL(getURL);
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.connect();

            // 取得输入流，并使用Reader读取
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            // 断开连接
            connection.disconnect();

            RespMsg respMsg = new Gson().fromJson(sb.toString(), RespMsg.class);
            return respMsg.getText();
        }catch (Exception e) {
            logger.error("error", e);
        }

        return "";
    }
}
