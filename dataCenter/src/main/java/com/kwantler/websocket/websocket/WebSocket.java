package com.kwantler.websocket.websocket;

import com.alibaba.fastjson.JSON;
import com.kwantler.websocket.entity.Message;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class WebSocket {
    /**
     * 会话
     */
    protected Session session;
    /**
     * 用户名称
     */
    protected String username;
    /**
     * 以用户的姓名为key，WebSocket为对象保存起来
     */
    protected static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>();

    /**
     * 连接开启
     */
    public abstract void onOpen(@PathParam("username") String username, Session session);

    /**
     * 连接出错
     * @param session 会话
     * @param error 错误信息
     */
    public abstract void onError(Session session, Throwable error);

    /**
     * 连接关闭
     */
    public abstract void onClose();

    /**
     * 收到客户端的消息
     *
     * @param message 消息
     * @param session 会话
     */
    public abstract void onMessage(String message, Session session);

    public boolean sendMessageToOnlineUser(Message message){
        if (message.getExcludeUsers() != null && message.getExcludeUsers().length > 0){
            Map<String, WebSocket> clientsClone = new HashMap<String, WebSocket>();
            clientsClone.putAll(clients);
            for (String username: message.getExcludeUsers()) {
                clientsClone.remove(username);
            }
            for (WebSocket w: clientsClone.values()) {
                w.session.getAsyncRemote().sendText(JSON.toJSONString(message));
            }
        }
        return true;
    }


    public void init(String username, Session session) {
        this.username = username;
        this.session = session;
        clients.put(username,this);
    }
}
