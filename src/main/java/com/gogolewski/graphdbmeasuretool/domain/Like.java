package com.gogolewski.graphdbmeasuretool.domain;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "LIKE")
public class Like {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private User likingUser;

    @EndNode
    private Post likedPost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getLikingUser() {
        return likingUser;
    }

    public void setLikingUser(User likingUser) {
        this.likingUser = likingUser;
    }

    public Post getLikedPost() {
        return likedPost;
    }

    public void setLikedPost(Post likedPost) {
        this.likedPost = likedPost;
    }
}
