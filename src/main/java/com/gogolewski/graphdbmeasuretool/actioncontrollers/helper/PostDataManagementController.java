package com.gogolewski.graphdbmeasuretool.actioncontrollers.helper;


import com.gogolewski.graphdbmeasuretool.dataaccess.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostDataManagementController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping(value = "/neo4j/deletePosts")
    public void deletePosts() {
        postRepository.deleteAll();
    }

}
