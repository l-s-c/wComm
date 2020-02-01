package com.lsc.userList.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lsc.userList.wCommUserListService;
import com.lsc.webSocket.wsHandler;

@Service
public class wCommUserListServiceImpl implements wCommUserListService{

	@Override
	public int sendAllUser(String msg) {
		return wsHandler.sendMsg(null, msg);
	}

}
