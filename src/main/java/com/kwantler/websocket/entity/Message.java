package com.kwantler.websocket.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Queue;
import java.util.Set;

public class Message implements Serializable {
    /**
     * 消息的发起者
     */
    private String fromUsername;
    /**
     * 消息的接受者
     */
    private String toUsername;
    /**
     * 消息内容
     */
    private String msg;
    /**
     * 消息类型
     */
    private int messageType;
    /**
     * 在线人员名单
     */
    private String[] onlineUsers;
    /**
     * 排除在外的名单
     */
    private String[] excludeUsers;
    /**
     * true:传送给所有的人,false:不传送给所有的人
     */
    private boolean sendAll;


    public boolean isSendAll() {
        return sendAll;
    }

    public void setSendAll(boolean sendAll) {
        this.sendAll = sendAll;
    }

    public String[] getExcludeUsers() {
        return excludeUsers;
    }

    public void setExcludeUsers(String[] excludeUsers) {
        this.excludeUsers = excludeUsers;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String[] getOnlineUsers() {
        return onlineUsers;
    }

    public void setOnlineUsers(String[] onlineUsers) {
        this.onlineUsers = onlineUsers;
    }

    @Override
    public String toString() {
        return "Message{" +
                "fromUsername='" + fromUsername + '\'' +
                ", toUsername='" + toUsername + '\'' +
                ", msg='" + msg + '\'' +
                ", messageType=" + messageType +
                ", onlineUsers=" + Arrays.toString(onlineUsers) +
                '}';
    }

    public Message(String msg, String[] excludeUsers, boolean sendAll) {
        this(msg,null,excludeUsers,sendAll);
    }

    public Message(String msg, String[] onlineUsers, String[] excludeUsers, boolean sendAll) {
        this(null,null,msg,-1,onlineUsers,excludeUsers,sendAll);
    }

    public Message(String fromUsername, String toUsername, String msg, int messageType) {
        this(fromUsername, toUsername, msg, messageType, null);
    }

    public Message(String fromUsername, String toUsername, String msg, int messageType, String[] onlineUsers) {
        this(fromUsername, toUsername, msg, messageType, onlineUsers,null, false);
    }

    public Message(String fromUsername, String toUsername, String msg, int messageType, String[] onlineUsers, String[] excludeUsers, boolean sendAll) {
        this.fromUsername = fromUsername;
        this.toUsername = toUsername;
        this.msg = msg;
        this.messageType = messageType;
        this.onlineUsers = onlineUsers;
        this.excludeUsers = excludeUsers;
        this.sendAll = sendAll;
    }
}
