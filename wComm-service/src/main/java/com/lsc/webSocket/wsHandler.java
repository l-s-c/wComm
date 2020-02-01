package com.lsc.webSocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Import;

import com.alibaba.dubbo.common.json.JSONObject;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.lsc.utils.JsonUtils;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import model.DataContent;
/**
 * 处理消息的handler
 * TextWebSocketFrame : 在netty 中，是用于websocket专门处理文本的对象，frame 是消息的载体
 * @author lsc
 *
 */
public class wsHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{
	private static Logger logger = LoggerFactory.getLogger(wsHandler.class);
	
	private static ChannelGroup users 
				   = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	public static Map<String,Channel> manager = new HashMap<String,Channel>();
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		Channel currentChannel = ctx.channel();
		
		//1.获取消息
		//获取客户端传输过来的消息
				String content = msg.text();
				System.out.println("接收到的数据："+content);
				logger.info("接收的数据："+content);
				DataContent dataContent = JsonUtils.jsonToPojo(content, DataContent.class);
				if(1==dataContent.getType()) {
					//首次连接方式关联
					manager.put(dataContent.getSendId(), currentChannel);
				}else {
					
				}
	}
	/**
	 * 客户端链接之后触发
	 * 获取客户端的channel 并且放到ChannelGroup中去进行管理
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		System.out.println(ctx.channel().id().asLongText()+"已连接");
		users.add(ctx.channel());
	}
	/**
	 * 客户端链接断开之后触发
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		//当触发此方法时，channelGroup 会自动移除对应的客户端的channel
		users.remove(ctx.channel());
		System.out.println("客户端断开");
		System.out.println("长Id:"+ctx.channel().id().asLongText());
		System.out.println("短Id:"+ctx.channel().id().asShortText());
		
		
	}
	/**
	 * 异常
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		//发生异常之后关闭channel,随后从channelGroup 中移除 
		ctx.channel().close();
		users.remove(ctx.channel());
	}
	/**
	 * 发送消息，指定或群发
	 * @param user 		用户集合 用于指定对应channel
	 * @param msg		消息
	 * @return
	 */
	public static int sendMsg(List<String> user,String msg) {
		if(CollectionUtils.isEmpty(user)) {
			//群发
			for(Channel channel : users) {
				channel.writeAndFlush(new TextWebSocketFrame(msg));
				//记录发送消息日志
			}
		}else {
			
		}
		return 1;
	}
	
}
