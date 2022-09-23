package edu.mum.cs544.BlogService.services;

import edu.mum.cs544.BlogService.dtos.UserPostDto;

import java.util.List;

public interface PostService {

    public List<UserPostDto> getAllPostsById(int id);

}
