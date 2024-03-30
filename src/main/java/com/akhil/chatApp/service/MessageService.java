package com.akhil.chatApp.service;

import com.akhil.chatApp.models.Message;
import com.akhil.chatApp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message createMessage(Message message){
        return messageRepository.save(message);
    }
}
