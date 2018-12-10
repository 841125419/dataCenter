package com.kwantler.websocket.entity;

/**
 * messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
 */
public class MessageType {
    public static final int ALIVE = 0;
    public static final int ON_LINE = 1;
    public static final int OFF_LINE = 2;
    public static final int ONLINE_LIST = 3;
    public static final int ORDINARY_MESSAGE = 4;
}
