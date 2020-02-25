package com.gogolewski.graphdbmeasuretool.domain;

import org.neo4j.ogm.annotation.*;

import javax.validation.constraints.NotNull;

@RelationshipEntity(type="LIKE")
public class Like {

    @Id @GeneratedValue
    private Long id;

    @StartNode @NotNull
    private User likingUser;

    @EndNode @NotNull
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
