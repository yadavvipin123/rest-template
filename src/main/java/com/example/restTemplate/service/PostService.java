package com.example.restTemplate.service;


import com.example.restTemplate.model.Post;
import com.example.restTemplate.model.PostResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Service
public class PostService {

   private final RestTemplate restTemplate;

    public PostService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Value("${test.url}")
    private String url;

    public PostResponse createPost(Post post){
        HttpEntity<Post> httpEntity = new HttpEntity<>(post);
        ResponseEntity<PostResponse> responseEntity = restTemplate.exchange(url +"/posts",  HttpMethod.POST, httpEntity, PostResponse.class);
        return responseEntity.getBody();
    }
    public Post getPost(Long id){
        Post post = new Post();
        post = restTemplate.getForObject(url + "/posts{id}",Post.class, id);
        log.info("get with id " + id + " successfully");
        return post;
    }
    public void deleteById(Long id){
        restTemplate.delete(url + "/posts{id}",id);
        log.info("delete with id " + id + " successfully");
    }
    public PostResponse updatePost(Long id,PostResponse postResponse){
        HttpEntity httpEntity = new HttpEntity<>(postResponse);
        ResponseEntity<PostResponse> responseEntity = restTemplate.exchange(url + "/books"+id,HttpMethod.PUT,httpEntity,PostResponse.class);
        return responseEntity.getBody();
    }
}
