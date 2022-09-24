package edu.mum.cs544.BlogService.services.impl;


import edu.mum.cs544.BlogService.dtos.ResponseDto;
import edu.mum.cs544.BlogService.dtos.UserDto;
import edu.mum.cs544.BlogService.dtos.UserPostDto;
import edu.mum.cs544.BlogService.models.User;
import edu.mum.cs544.BlogService.security.BlogUserDetails;
import edu.mum.cs544.BlogService.services.PostService;
import edu.mum.cs544.BlogService.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PostService postService;
    private final RestTemplate restTemplate;

    private final static String USERS_URL = "http://localhost:9090/api/v1/users";


    @Override
    public User loadUserByUsername(String username) {
        try {
            ParameterizedTypeReference<ResponseDto<User>> parameterizedTypeReference = new ParameterizedTypeReference<ResponseDto<User>>() {
            };
            ResponseEntity<ResponseDto<User>> response =
                    restTemplate.exchange(USERS_URL + "/getUserByUsername?username={username}", HttpMethod.GET, null, parameterizedTypeReference, username);
            ResponseDto<User> usersResponse = response.getBody();
            return usersResponse.getResponse();
        } catch (HttpClientErrorException ex) {
            throw ex;
        }

    }

    @Override
    public UserDto getUserById(int id) {
        try {
            ParameterizedTypeReference<ResponseDto<UserDto>> parameterizedTypeReference = new ParameterizedTypeReference<ResponseDto<UserDto>>() {
            };
            ResponseEntity<ResponseDto<UserDto>> response =
                    restTemplate.exchange(USERS_URL + "/{id}", HttpMethod.GET, null, parameterizedTypeReference, id);
            ResponseDto<UserDto> usersResponse = response.getBody();
            return usersResponse.getResponse();

        } catch (HttpClientErrorException ex) {
            throw ex;
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        try {
            ParameterizedTypeReference<ResponseDto<List<UserDto>>> parameterizedTypeReference = new ParameterizedTypeReference<ResponseDto<List<UserDto>>>() {
            };
            ResponseEntity<ResponseDto<List<UserDto>>> response =
                    restTemplate.exchange(USERS_URL, HttpMethod.GET, null, parameterizedTypeReference, "");
            ResponseDto<List<UserDto>> usersResponse = response.getBody();
            return usersResponse.getResponse();
        } catch (HttpClientErrorException ex) {
            throw ex;
        }
    }

    @Override
    public UserDto deleteUser(int id) {
        try {
            ParameterizedTypeReference<ResponseDto<UserDto>> parameterizedTypeReference = new ParameterizedTypeReference<ResponseDto<UserDto>>() {
            };
            ResponseEntity<ResponseDto<UserDto>> response =
                    restTemplate.exchange(USERS_URL+"/{id}", HttpMethod.DELETE, null, parameterizedTypeReference, id);
            ResponseDto<UserDto> userResponse = response.getBody();
            return userResponse.getResponse();
        } catch (HttpClientErrorException ex) {
            throw ex;
        }
    }

    @Override
    public UserDto update(UserDto user, int id) {
        try {
            ParameterizedTypeReference<ResponseDto<UserDto>> parameterizedTypeReference = new ParameterizedTypeReference<ResponseDto<UserDto>>() {
            };
            HttpEntity<UserDto> request = new HttpEntity<>(user);
            ResponseEntity<ResponseDto<UserDto>> response =
                    restTemplate.exchange(USERS_URL+"/{id}", HttpMethod.PUT, request, parameterizedTypeReference, id);
            ResponseDto<UserDto> userResponse = response.getBody();
            return userResponse.getResponse();
        } catch (HttpClientErrorException ex) {
            throw ex;
        }

    }

    @Override
    public List<UserPostDto> getAllPosts() {
        var user = (BlogUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var postList = postService.getAllPostsByUserId(user.getId());
        //var postList = postService.getAllPosts();// can return all posts for admin.
        return  postList;
    }

    @Override
    public List<UserPostDto> getAllPostsByUserId(int id) {
        var postList = postService.getAllPostsByUserId(id);
        return  postList;
    }

}
