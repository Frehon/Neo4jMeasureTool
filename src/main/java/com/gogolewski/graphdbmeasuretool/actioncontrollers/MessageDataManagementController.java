package com.gogolewski.graphdbmeasuretool.actioncontrollers;


import com.gogolewski.graphdbmeasuretool.dataaccess.MessageRepository;
import com.gogolewski.graphdbmeasuretool.dataaccess.UserRepository;
import com.gogolewski.graphdbmeasuretool.domain.Message;
import com.gogolewski.graphdbmeasuretool.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class MessageDataManagementController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/neo4j/loadMessages")
    public void setupUserData() {
        Message message = new Message();
        List<User> users = (List<User>) userRepository.findAll();

        message.setMessageSender(users.get(0));
        message.setMessageReceiver(users.get(1));
        message.setMessagePayload("This is an example message");

        messageRepository.save(message);
    }

    @GetMapping(value = "neo4j/getMessages")
    public Set<Message> getMessages() {
        return messageRepository.myFindAll();
    }

    @GetMapping(value = "neo4j/deleteMessages")
    public void deleteMessages() {
        messageRepository.deleteAll();
    }
}
