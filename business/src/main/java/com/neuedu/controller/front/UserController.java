package com.neuedu.controller.front;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.User;
import com.neuedu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
     IUserService userService;


    /**
     * 注册接口
     * */

    @RequestMapping(value = "register.do")
    public ServerResponse register(User user){

     return  userService.register(user);

    }


}
