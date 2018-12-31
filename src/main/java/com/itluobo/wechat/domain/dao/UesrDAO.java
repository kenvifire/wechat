package com.itluobo.wechat.domain.dao;

import com.itluobo.wechat.domain.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by kenvizhu on 15/7/6.
 */
@Repository
public interface UesrDAO {

    public Long inserUser(User user);

}
