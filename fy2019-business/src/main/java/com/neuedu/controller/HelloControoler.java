package com.neuedu.controller;

import com.neuedu.config.AppConfig;
import com.neuedu.dao.ILoginDao;
import com.neuedu.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class HelloControoler {


/*    @Value("${limit.minMoney}")
    private BigDecimal minMoney;
    @Value("${limit.maxMoney}")
    private BigDecimal maxMoney;*/

   @Autowired
   AppConfig appConfig;



   // @RequestMapping(value = {"/hello","/hello2"})
    @PostMapping(value = {"/hello","/hello2"})
    public String hello(@RequestParam(value = "username",required = false,defaultValue = "admin") String username){

        return username;
    }

    @RequestMapping("/login/{username}/{password}")
    public  String  testrestful(@PathVariable("username") String username,@PathVariable("password")String password){

        return username+" "+password;
    }

    @Autowired
    ILoginDao loginDao;
    @RequestMapping(value = "/user/login")
    public Admin login(String username,String password){

        return loginDao.findAdminByUsernameAndPassword(username,password);
    }

}
