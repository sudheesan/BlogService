package edu.mum.cs544.BlogService.services;

import edu.mum.cs544.BlogService.dtos.UserPostDto;

import java.util.List;

public interface PostService {

    public List<UserPostDto> getAllPostsByUserId(int id);
    public List<UserPostDto> getAllPosts();
    public UserPostDto getById(int id);
    public UserPostDto delete(int id);
    public UserPostDto update(UserPostDto post);
    public UserPostDto add(UserPostDto post);
}
