package com.itluobo.wechat.uitls;

import com.itluobo.wechat.domain.UserMessage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by hannahzhang on 15/6/11.
 */
public class IOUtils {

    public static void writeMsg(UserMessage userMessage, OutputStream outputStream) throws IOException{

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
        bw.write(MessageUtils.convertMsg(userMessage));

    }
}
