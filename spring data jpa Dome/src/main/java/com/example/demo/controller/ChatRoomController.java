package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.demo.utils.WebSocketUtil.seesionMap;
import static com.example.demo.utils.WebSocketUtil.sendMessageToALL;
import static com.example.demo.utils.WebSocketUtil.sendMessagee;

@RestController
@ServerEndpoint("/chatRoom/{username}")
public class ChatRoomController {
    //输出改控制器的日志
    private  static  final Logger log = LoggerFactory.getLogger(ChatRoomController.class);

    @OnOpen
    public  void openSession(@PathParam("username") String username, Session session){
        seesionMap.put(username,session);
        log.info(username+"对应的session"+session);
        String message ="##欢迎："+username+"来到聊天室";
        sendMessageToALL(message);
    }
    @OnMessage
    public  void  OnMessage (@PathParam("username") String username,String message){
        log.info("用户："+username+" 发言："+message);
        sendMessageToALL(username+":"+message);
    }
    @OnClose
    public  void onClose(@PathParam("username") String username,Session session){
        log.info("用户"+username+":离开聊天室");
        seesionMap.remove(username);
        sendMessageToALL("用户["+username+"]:离开聊天室,欢迎下次光临");
        try {
            session.close();
        }
        catch (IOException e){

        }
    }
    @GetMapping("/chatRoom/{sendr}/to/{recevice}")
    public  void onMessage(@PathVariable("sendr")String sender, @PathVariable("recevice")String recevice,String message){
        if(seesionMap.containsKey(recevice)){
        sendMessagee(seesionMap.get(recevice),"["+sender+"]:-> ["+recevice+"]:"+message);
        }
  }
    @PostMapping("/chatRoom/getOnlineChatFriend/{username}")
    public Object getOnlineChatFriend(@PathVariable("username")String username){
        List<Map<String,Object>> friendLst = new ArrayList<>();
        seesionMap.forEach((s, session) -> {
            Map<String,Object> friendMap = new HashMap<>();
           if(!s.equals(username)){
               friendMap.put("value",s);
               friendMap.put("text",s);
               friendLst.add(friendMap);
           }

        });
        return friendLst;
    }

}
