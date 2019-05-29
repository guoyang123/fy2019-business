package com.neuedu.controller.front;

import com.neuedu.common.ResponseCode;
import com.neuedu.common.RoleEnum;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.User;
import com.neuedu.service.ICartService;
import com.neuedu.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/cart/")
public class CartController {


    @Autowired
    ICartService cartService;
    /**
     * 添加商品到购物车
     * */
    @RequestMapping("add/{productId}/{count}")
   public ServerResponse  addCart(@PathVariable("productId")Integer productId,
                                  @PathVariable("count")Integer count,
                                  HttpSession session){


    User user=(User)session.getAttribute(Const.CURRENT_USER);

        return  cartService.addProductToCart(user.getId(),productId,count);
   }


}
