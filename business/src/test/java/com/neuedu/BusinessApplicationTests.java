package com.neuedu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusinessApplicationTests {

	@Test
	public void contextLoads() {


		 BigDecimal a=new BigDecimal("0.02");
		BigDecimal b=new BigDecimal("0.03");
		System.out.println(a.add(b));



	}

}
