package com.example.demo.utils;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//用于发送消息等
public final class WebSocketUtil {

    //定义一个webSocket 用户seesion存储使用
    public   static  final  Map<String ,Session> seesionMap = new ConcurrentHashMap<>();

    //发送消息到所有用户
    public  static  final void sendMessageToALL(String message){
        seesionMap.forEach((s, session) -> {
            sendMessagee(session,message);
        });
    }
    //发送具体某个session的用户。
    public static void sendMessagee(Session session ,String message){
        if(session==null) return;
        final RemoteEndpoint.Basic basic = session.getBasicRemote();
        if (basic == null) {
            return;
        }
        try {
            basic.sendText(message);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
