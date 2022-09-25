package edu.mum.cs544.BlogService.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.cs544.BlogService.dtos.ResponseDto;
import edu.mum.cs544.BlogService.dtos.UserPostDto;
import edu.mum.cs544.BlogService.services.PostService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/blogs/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;


    @GetMapping("/{id}")
    public ResponseDto<UserPostDto> getPostById(@PathVariable int id) {
        UserPostDto post = postService.getById(id);
        return new ResponseDto<>("Post", false, post, null);
    }

    @PutMapping()
    public ResponseEntity<ResponseDto<UserPostDto>> updatePost(@RequestBody UserPostDto postDto) {
        UserPostDto post = postService.update(postDto);
        return ResponseEntity.ok().body(new ResponseDto<>("Post Updated", false, post, null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<UserPostDto>> deletePost(@PathVariable int id) {
        UserPostDto post = postService.delete(id);
        return ResponseEntity.ok().body(new ResponseDto<>("Post deleted", false, post, null));
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDto<UserPostDto>> addPost(@RequestBody UserPostDto postDto) {
        UserPostDto post = postService.add(postDto);
        return ResponseEntity.ok().body(new ResponseDto<>("Post added", false, post, null));
    }

    //--getAllPosts in UserController.java    


}
