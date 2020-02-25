package com.gogolewski.graphdbmeasuretool.actioncontrollers;

import com.gogolewski.graphdbmeasuretool.dataaccess.PostRepository;
import com.gogolewski.graphdbmeasuretool.dataaccess.UserRepository;
import com.gogolewski.graphdbmeasuretool.domain.Post;
import com.gogolewski.graphdbmeasuretool.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class UserDataManagementController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping(value = "/neo4j/loadUsers")
    public void setupUserData() {
        User user1 = new User();
        user1.setName("Tito");

        // create post by user
        Post post = new Post();
        post.setAuthor(user1);
        post.setPostPhotoPath("path");
        post.setPostTextContent("some content");

        User user2 = new User();
        user2.setName("Julita");

        userRepository.saveAll(Arrays.asList(user1, user2));
        postRepository.save(post);
    }

    @GetMapping(value = "neo4j/getUsers")
    public Iterable getUsers() {
        return userRepository.findAll();
    }

    @GetMapping(value = "neo4j/deleteUsers")
    public void deleteUsers() {
        userRepository.deleteAll();
    }
}
