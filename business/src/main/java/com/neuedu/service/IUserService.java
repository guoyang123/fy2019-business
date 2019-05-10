package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.User;

public interface IUserService {

    /**
     * 注册接口
     * @param user
     * @return ServerResponse
     * */

    public ServerResponse register(User user);

}
