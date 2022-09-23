package edu.mum.cs544.BlogService.services;

import edu.mum.cs544.BlogService.dtos.*;
import edu.mum.cs544.BlogService.models.User;

import java.util.List;

public interface UserService {
    public User loadUserByUsername(String username);
    public UserDto getUserById(int id);
    public List<UserDto> getAllUsers();
    public UserDto deleteUser(int id);
    public UserDto update(UserDto user, int id);
    public List<UserPostDto> getAllPosts();
    public List<UserPostDto> getAllPostsById(int id);
}
