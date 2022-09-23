package edu.mum.cs544.BlogService.services;

import edu.mum.cs544.BlogService.dtos.LoginRequest;
import edu.mum.cs544.BlogService.dtos.LoginResponse;
import edu.mum.cs544.BlogService.dtos.ResponseDto;
import edu.mum.cs544.BlogService.dtos.UserDto;
import edu.mum.cs544.BlogService.models.User;

import java.util.List;

public interface UserService {
    public User loadUserByUsername(String username);
    public UserDto getUserById(int id);
    public List<UserDto> getAllUsers();
    public UserDto deleteUser(int id);
    public UserDto update(UserDto user, int id);
}
