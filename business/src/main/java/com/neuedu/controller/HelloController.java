package com.neuedu.controller;

import com.neuedu.dao.CartMapper;
import com.neuedu.pojo.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    CartMapper cartMapper;
    @RequestMapping("/login/{username}/{password}")
    public List<Cart> testrestful(@PathVariable("username") String username, @PathVariable("password")String password){

        List<Cart> userList= cartMapper.selectAll();
        return userList;

    }
}
