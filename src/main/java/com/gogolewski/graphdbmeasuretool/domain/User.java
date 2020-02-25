package com.gogolewski.graphdbmeasuretool.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Relationship(type = "IS FRIEND OF", direction = Relationship.UNDIRECTED)
    private Set<User> friends = new HashSet<>();

    @Relationship(type = "CREATES")
    private Set<Post> posts = new HashSet<>();

    @Relationship(type = "GIVE")
    private Set<Like> givenLikes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Like> getGivenLikes() {
        return givenLikes;
    }

    public void setGivenLikes(Set<Like> givenLikes) {
        this.givenLikes = givenLikes;
    }
}
