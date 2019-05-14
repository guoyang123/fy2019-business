package com.neuedu.controller.front;

import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.User;
import com.neuedu.service.IUserService;
import com.neuedu.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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


    /**
     * 登录接口
     * */

    @RequestMapping(value = "login/{username}/{password}")
    public ServerResponse login(@PathVariable("username")String username,
                                @PathVariable("password")String password,
                                HttpSession session){


        ServerResponse serverResponse=userService.login(username,password,1);
        //判断是否登录成功
        if(serverResponse.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,serverResponse.getData());
        }

        return serverResponse;
    }

    /**
     * 根据username获取密保问题
     * */

    @RequestMapping("forget_get_question/{username}")
    public ServerResponse forget_get_question(@PathVariable("username") String username){

        return userService.forget_get_question(username);
    }




    /**
     * 提交答案
     * */

    @RequestMapping("forget_check_answer.do")
    public ServerResponse forget_check_answer( String username,String question,String answer){


        return userService.forget_check_answer(username, question, answer);
    }



    /**
     * 修改密码
     * */

    @RequestMapping("forget_reset_password.do")
    public ServerResponse forget_reset_password( String username,String newpassword,String forgettoken){



        return userService.forget_reset_password(username, newpassword, forgettoken);
    }


    /**
     * 修改用户信息
     *
     * */

    @RequestMapping(value = "update_information.do")
    public ServerResponse update_information(User user,HttpSession session){

        User loginUser=(User)session.getAttribute(Const.CURRENT_USER);
        if(loginUser==null){
            return ServerResponse.serverResponseByError(ResponseCode.NOT_LOGIN,"未登录");
        }
        user.setId(loginUser.getId());
        ServerResponse serverResponse= userService.update_information(user);
        return serverResponse;
    }

}
