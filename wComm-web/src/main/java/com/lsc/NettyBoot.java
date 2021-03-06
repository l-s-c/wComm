package com.lsc;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.lsc.webSocket.wsServer;


@Component
public class NettyBoot implements ApplicationListener<ContextRefreshedEvent>{
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null) {
			//启动netty服务器
			try {
				wsServer.getInstance().start();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
