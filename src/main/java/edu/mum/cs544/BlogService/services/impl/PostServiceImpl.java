package edu.mum.cs544.BlogService.services.impl;

import edu.mum.cs544.BlogService.dtos.ResponseDto;
import edu.mum.cs544.BlogService.dtos.UserPostDto;
import edu.mum.cs544.BlogService.models.Post;
import edu.mum.cs544.BlogService.security.BlogUserDetails;
import edu.mum.cs544.BlogService.services.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;
    @Value("${service.post.url}")
    private String POSTS_URL;


    @Override
    public List<UserPostDto> getAllPostsByUserId(int id) {
        try {
            ParameterizedTypeReference<ResponseDto<List<Post>>> parameterizedTypeReference = new ParameterizedTypeReference<ResponseDto<List<Post>>>() {
            };
            ResponseEntity<ResponseDto<List<Post>>> response = restTemplate.exchange(POSTS_URL + "?userId={id}", HttpMethod.GET,
                    null, parameterizedTypeReference, id);
            ResponseDto<List<Post>> postsForId = response.getBody();
            return postsForId.getResponse().stream().map(p -> modelMapper.map(p, UserPostDto.class)).collect(Collectors.toList());
        } catch (HttpClientErrorException ex) {
            throw ex;
        }
    }

    @Override
    public List<UserPostDto> getAllPosts() {
        try {
            ParameterizedTypeReference<ResponseDto<List<Post>>> parameterizedTypeReference = new ParameterizedTypeReference<ResponseDto<List<Post>>>() {
            };
            ResponseEntity<ResponseDto<List<Post>>> response = restTemplate.exchange(POSTS_URL + "/", HttpMethod.GET,
                    null, parameterizedTypeReference);
            ResponseDto<List<Post>> postsForId = response.getBody();
            return postsForId.getResponse().stream().map(p -> modelMapper.map(p, UserPostDto.class)).collect(Collectors.toList());
        } catch (HttpClientErrorException ex) {
            throw ex;
        }
    }

    @Override
    public UserPostDto getById(int id) {
        try {
            ParameterizedTypeReference<ResponseDto<Post>> parameterizedTypeReference = new ParameterizedTypeReference<ResponseDto<Post>>() {
            };
            ResponseEntity<ResponseDto<Post>> response = restTemplate.exchange(POSTS_URL + "/{id}",
                    HttpMethod.GET, null, parameterizedTypeReference, id);
            ResponseDto<Post> userResponse = response.getBody();
            return modelMapper.map(userResponse.getResponse(), UserPostDto.class);
        } catch (HttpClientErrorException ex) {
            throw ex;
        }
    }

    @Override
    public UserPostDto delete(int id) {
        try {
            ParameterizedTypeReference<ResponseDto<UserPostDto>> parameterizedTypeReference = new ParameterizedTypeReference<ResponseDto<UserPostDto>>() {
            };
            ResponseEntity<ResponseDto<UserPostDto>> response = restTemplate.exchange(POSTS_URL + "/delete/{id}",
                    HttpMethod.GET, null, parameterizedTypeReference, id);
            ResponseDto<UserPostDto> userResponse = response.getBody();
            return userResponse.getResponse();
        } catch (HttpClientErrorException ex) {
            throw ex;
        }
    }

    @Override
    public UserPostDto update(UserPostDto postDto, int id) {
        try {
            ParameterizedTypeReference<ResponseDto<UserPostDto>> parameterizedTypeReference = new ParameterizedTypeReference<ResponseDto<UserPostDto>>() {
            };
            var user = (BlogUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Post post = modelMapper.map(postDto, Post.class);
            post.setUserId(user.getId());

            HttpEntity<Post> request = new HttpEntity<>(post);
            ResponseEntity<ResponseDto<UserPostDto>> response = restTemplate.exchange(POSTS_URL + "/update/{id}",
                    HttpMethod.POST, request, parameterizedTypeReference, id);
            ResponseDto<UserPostDto> userResponse = response.getBody();
            return userResponse.getResponse();
        } catch (HttpClientErrorException ex) {
            throw ex;
        }
    }

    @Override
    public UserPostDto add(UserPostDto postDto) {
        try {
            ParameterizedTypeReference<ResponseDto<UserPostDto>> parameterizedTypeReference = new ParameterizedTypeReference<ResponseDto<UserPostDto>>() {
            };
            var user = (BlogUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Post post = modelMapper.map(postDto, Post.class);
            post.setUserId(user.getId());
            HttpEntity<Post> request = new HttpEntity<>(post);
            ResponseEntity<ResponseDto<UserPostDto>> response = restTemplate.exchange(POSTS_URL + "/add",
                    HttpMethod.POST, request, parameterizedTypeReference);
            ResponseDto<UserPostDto> userResponse = response.getBody();
            return userResponse.getResponse();
        } catch (HttpClientErrorException ex) {
            throw ex;
        }
    }

}
