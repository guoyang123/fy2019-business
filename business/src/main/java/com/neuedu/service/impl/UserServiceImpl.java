package com.neuedu.service.impl;

import com.neuedu.common.ResponseCode;
import com.neuedu.common.RoleEnum;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.UserMapper;
import com.neuedu.pojo.User;
import com.neuedu.service.IUserService;
import com.neuedu.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse register(User user) {

     //step1:参数校验
     if(user==null){
        return ServerResponse.serverResponseByError(ResponseCode.PARAM_NOT_NULL,"参数不能为空");
     }
     //step2: 判断用户名是否存在
     int result=userMapper.isexistsusername(user.getUsername());
     if(result>0){//用户名已存在
         return ServerResponse.serverResponseByError(ResponseCode.USERNAME_EXISTS,"用户名已存在");
     }
     //step3:判断邮箱是否存在
        int resultemail=userMapper.isexistsemail(user.getEmail());
        if(resultemail>0){//邮箱已存在
            return ServerResponse.serverResponseByError(ResponseCode.EMAIL_EXISTS,"邮箱已存在");
        }
     //step4: MD5密码加密，设置用户角色 ADMIN ---XXXXXXXXXXXXX
       user.setPassword(MD5Utils.getMD5Code(user.getPassword()));
        //设置角色为普通用户
        user.setRole(RoleEnum.ROLE_USER.getRole());
     //step5: 注册
       int insertResult= userMapper.insert(user);
       if(insertResult<=0){
           return ServerResponse.serverResponseByError(ResponseCode.ERROR,"注册失败");
       }
     //step6：返回
      return ServerResponse.serverResponseBySuccess();
    }
}
