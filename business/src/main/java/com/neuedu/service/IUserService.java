package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public interface IUserService {



    public  String  testAOP(String param);

    /**
     * 注册接口
     * @param user
     * @return ServerResponse
     * */

    public ServerResponse register(User user);

    /**
     * 登录接口
     * @param username password
     *                 type 1:普通用户 0：管理员
     * @return ServerResponse
     * */

    public ServerResponse login(String username,String password,int type);

    /**
     * 根据username获取密保问题
     * */


    public ServerResponse forget_get_question(@PathVariable("username") String username);

    /**
     * 提交答案
     * */


    public ServerResponse forget_check_answer( String username,String question,String answer);

    /**
     * 修改密码
     * */


    public ServerResponse forget_reset_password( String username,String newpassword,String forgettoken);
    /**
     * 修改密码
     * */
    public ServerResponse update_information(User user);
}
