package com.gogolewski.graphdbmeasuretool.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Post {

    @Id
    @GeneratedValue
    private Long id;
    private String postTextContent;
    private String postPhotoPath;
    private Set<Like> likes = new HashSet<>();

    @Relationship(type = "CREATED", direction = Relationship.INCOMING)
    private User author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostTextContent() {
        return postTextContent;
    }

    public void setPostTextContent(String postTextContent) {
        this.postTextContent = postTextContent;
    }

    public String getPostPhotoPath() {
        return postPhotoPath;
    }

    public void setPostPhotoPath(String postPhotoPath) {
        this.postPhotoPath = postPhotoPath;
    }

    public Set<Like> getLikes() {
        return likes;
    }

    public void setLikes(Set<Like> likes) {
        this.likes = likes;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
