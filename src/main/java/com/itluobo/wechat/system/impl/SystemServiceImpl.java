package com.itluobo.wechat.system.impl;

import com.itluobo.wechat.system.SystemService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service("sys")
public class SystemServiceImpl implements SystemService {

    @RequestMapping("/ping")
    @Override
    public String ping() {
        return "SUCCESS";
    }
}
