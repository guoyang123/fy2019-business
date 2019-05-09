package com.neuedu.controller;

import com.neuedu.config.AppConfig;
import com.neuedu.dao.CartMapper;
import com.neuedu.pojo.Cart;
import com.neuedu.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

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
    public  List<Cart>  testrestful(@PathVariable("username") String username,@PathVariable("password")String password){

        List<Cart> userList= cartMapper.selectAll();
        return userList;

    }


    @Autowired
    CartMapper cartMapper;





}
