package com.akhil.chatApp.controllers;

import com.akhil.chatApp.models.Message;
import com.akhil.chatApp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


import java.util.List;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @QueryMapping("messages")
    public List<Message> getAllMessages(){
        return messageService.getAllMessages();
    }

    @MutationMapping("createMessage")
    public Message createMessage(@Argument MessageInput message){
        Message m = new Message();
        m.setSender(message.getSender());
        m.setContent(message.getContent());
        Message savedMessage =  this.messageService.createMessage(m);

        this.simpMessagingTemplate.convertAndSend("/topic/newMessage", savedMessage);
        return savedMessage;
    }

}

class MessageInput {
    private String content;
    private String sender;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
