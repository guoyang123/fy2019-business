package com.neuedu;

import com.neuedu.listener.AppClosedListener;
import com.neuedu.listener.AppStartListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.neuedu.dao")
public class BusinessApplication {

	public static void main(String[] args) {

		SpringApplication sa=new SpringApplication(BusinessApplication.class);
		sa.addListeners(new AppStartListener());
		sa.addListeners(new AppClosedListener());
		sa.run(args);

		//SpringApplication.run(BusinessApplication.class, args);
	}

}
