package com.kwantler.websocket.websocket;

import com.alibaba.fastjson.JSON;
import com.kwantler.websocket.entity.Message;
import com.kwantler.websocket.entity.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Date;

@Component
@ServerEndpoint("/webSocket/alive/{username}")
public class AliveWebSocket extends WebSocket{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
        System.out.println(new Date()+"：websocket连接开启");
        init(username,session);
        Message message = new Message(username+"上线",MessageType.ON_LINE, new String[]{username},true);
        sendMessageToOnlineUser(message);
    }

    @Override
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println(new Date()+"：websocket连接出错"+error.getMessage());
    }

    @Override
    @OnClose
    public void onClose() {
        System.out.println(new Date()+"：websocket连接关闭");
        clients.remove(username);
        Message message = new Message(username+"已经下线",MessageType.OFF_LINE,new String[]{},true);
        sendMessageToOnlineUser(message);
    }

    @Override
    @OnMessage
    public void onMessage(String message, Session session) {
        Message msg = JSON.parseObject(message,Message.class);
        System.out.println(new Date()+"：websocket接收消息"+message+"/n解析后的为："+msg.toString());
    }
}
