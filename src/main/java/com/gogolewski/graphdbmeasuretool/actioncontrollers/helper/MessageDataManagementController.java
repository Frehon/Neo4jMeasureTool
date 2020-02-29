package com.gogolewski.graphdbmeasuretool.actioncontrollers.helper;

import com.gogolewski.graphdbmeasuretool.dataaccess.MessageRepository;
import com.gogolewski.graphdbmeasuretool.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class MessageDataManagementController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping(value = "neo4j/getMessages")
    public Set<Message> getMessages() {
        return messageRepository.myFindAll();
    }

    @GetMapping(value = "neo4j/deleteMessages")
    public void deleteMessages() {
        messageRepository.deleteAll();
    }
}
