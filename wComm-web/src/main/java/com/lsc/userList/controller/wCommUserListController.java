package com.lsc.userList.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lsc.userList.wCommUserListService;
import com.lsc.utils.JsonResult;
import com.lsc.webSocket.wsHandler;

import io.netty.channel.Channel;

/**
 * 实时在线用户操作类
 * @author lsc
 *
 */
@Controller
@RequestMapping("/user")
public class wCommUserListController {
	private static Logger logger = LoggerFactory.getLogger(wCommUserListController.class);
	
	@Reference
	private wCommUserListService wCommUserListService; 
	
	@RequestMapping("/index")
	public String index() {
		return "userList";
	}
	/**
	 * 获取所有在线用户
	 * @return
	 */
	@RequestMapping("/getUserList")
	@ResponseBody
	public JsonResult getUserList() {
		logger.info("获取所有在线用户");
		Map<String,Channel> data = wsHandler.manager;
		return JsonResult.ok(data);
	}
	/**
	 * 群发
	 * @param msg
	 * @return
	 */
	@RequestMapping("/sendAll")
	@ResponseBody
	public JsonResult sendAll(String msg) {
		logger.info("群发，参数:{}",msg);
		try {
			wCommUserListService.sendAllUser(msg);
		}catch(Exception e) {
			e.printStackTrace();
			return JsonResult.fail("发送失败");
		}
		return JsonResult.ok();
	}
}
