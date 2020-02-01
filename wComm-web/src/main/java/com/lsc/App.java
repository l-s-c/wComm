package com.lsc;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

/**
 * 启动类
 * @author lsc
 *
 */
@ComponentScan(basePackages= {"com.lsc"})
@SpringBootApplication(scanBasePackages= {"com.lsc"})
@Controller
@MapperScan("com.lsc")
@EnableDubbo
public class App {
	private static Logger logger  = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) {
		logger.info("项目启动,Start...");
		SpringApplication.run(App.class, args);
	}
}
