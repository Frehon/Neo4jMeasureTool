package com.gogolewski.graphdbmeasuretool.dataaccess;

import com.gogolewski.graphdbmeasuretool.domain.Message;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Set;

public interface MessageRepository extends Neo4jRepository<Message, Long> {

    @Query("match (message: Message) return message")
    Set<Message> myFindAll();
}
