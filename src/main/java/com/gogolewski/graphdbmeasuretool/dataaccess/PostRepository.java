package com.gogolewski.graphdbmeasuretool.dataaccess;

import com.gogolewski.graphdbmeasuretool.domain.Post;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PostRepository extends Neo4jRepository<Post, Long> {
}
