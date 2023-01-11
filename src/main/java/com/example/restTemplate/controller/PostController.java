package com.example.restTemplate.controller;
import com.example.restTemplate.model.Post;
import com.example.restTemplate.model.PostResponse;
import com.example.restTemplate.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping(value = "/posts",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<PostResponse> createPost(@RequestBody Post post){
        PostResponse postResponse = postService.createPost(post);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }
   @GetMapping(value = "/posts{id}",consumes =MediaType.APPLICATION_JSON_VALUE )
        public ResponseEntity<Post> getPost(@PathVariable Long id){
        Post post =postService.getPost(id);
        return new ResponseEntity<>(post,HttpStatus.OK);
   }
   @DeleteMapping(value = "/posts{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostResponse> deleteById(@PathVariable Long id){
        postService.deleteById(id);
        return ResponseEntity.ok().build();
   }
   @PutMapping(value = "/posts{id}" ,consumes = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<PostResponse> updatePost(@PathVariable Long id, @RequestBody PostResponse post) {
       PostResponse postResponse = postService.updatePost(id, post);
       return new ResponseEntity<>(postResponse, HttpStatus.OK);

   }

}
