package com.gogolewski.graphdbmeasuretool.dataaccess;

import com.gogolewski.graphdbmeasuretool.domain.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {

    @Query("MATCH (user:User) RETURN count(user)")
    long count();

    @Query("MATCH (user:User) RETURN user LIMIT {0}")
    List<User> findAllWithLimit(long usersAmount);
}
