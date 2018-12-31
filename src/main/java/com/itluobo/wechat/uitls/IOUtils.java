package com.itluobo.wechat.uitls;

import com.itluobo.wechat.domain.entity.UserMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by kenvizhu on 15/6/11.
 */
public class IOUtils {

    private static final Logger logger = LogManager.getLogger(IOUtils.class);

    public static void writeMsg(UserMessage userMessage, OutputStream outputStream) throws IOException {

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
        bw.write(MessageUtils.convertMsg(userMessage));

    }

    public static String readMessage(final HttpServletRequest request) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));){

            StringBuilder stringBuilder = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();

        } catch (IOException e) {
            logger.error("error when reading input stream", e);
        }

        return "";
    }

}
