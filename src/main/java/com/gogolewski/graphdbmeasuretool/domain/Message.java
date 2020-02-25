package com.gogolewski.graphdbmeasuretool.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Message {

    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = " SEND", direction = Relationship.INCOMING)
    private User messageSender;

    @Relationship(type = "RECEIVED")
    private User messageReceiver;

    private String messagePayload;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(User messageSender) {
        this.messageSender = messageSender;
    }

    public User getMessageReceiver() {
        return messageReceiver;
    }

    public void setMessageReceiver(User messageReceiver) {
        this.messageReceiver = messageReceiver;
    }

    public String getMessagePayload() {
        return messagePayload;
    }

    public void setMessagePayload(String messagePayload) {
        this.messagePayload = messagePayload;
    }
}
