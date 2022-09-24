package edu.mum.cs544.BlogService.services.impl;

import edu.mum.cs544.BlogService.dtos.ResponseDto;
import edu.mum.cs544.BlogService.dtos.UserPostDto;
import edu.mum.cs544.BlogService.models.Post;
import edu.mum.cs544.BlogService.services.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

    private final static String POSTS_URL = "http://localhost:7070/api/v1/posts";

    @Override
    public List<UserPostDto> getAllPostsByUserId(int id) {
        try {
            ParameterizedTypeReference<List<Post>> parameterizedTypeReference = new ParameterizedTypeReference<List<Post>>() {
            };
            ResponseEntity<List<Post>> response = restTemplate.exchange(POSTS_URL + "?userId={id}", HttpMethod.GET,
                    null, parameterizedTypeReference, id);
            List<Post> postsForId = response.getBody();
            return postsForId.stream().map(p -> modelMapper.map(p, UserPostDto.class)).collect(Collectors.toList());
        } catch (HttpClientErrorException ex) {
            throw ex;
        }
    }


    @Override
    public UserPostDto getById(int id) {
        try {
            ParameterizedTypeReference<ResponseDto<UserPostDto>> parameterizedTypeReference = new ParameterizedTypeReference<ResponseDto<UserPostDto>>() {
            };
            ResponseEntity<ResponseDto<UserPostDto>> response = restTemplate.exchange(POSTS_URL + "/{id}",
                    HttpMethod.GET, null, parameterizedTypeReference, id);
            ResponseDto<UserPostDto> userResponse = response.getBody();
            return userResponse.getResponse();
        } catch (HttpClientErrorException ex) {
            throw ex;
        }
    }

    // @Override
    // public UserPostDto getPostById(int id) {
    //     try {
    //         ParameterizedTypeReference<ResponseDto<Post>> parameterizedTypeReference = new ParameterizedTypeReference<ResponseDto<Post>>() {
    //         };
    //         ResponseEntity<ResponseDto<Post>> response = restTemplate.exchange(POSTS_URL + "/{id}",
    //                 HttpMethod.GET, null, parameterizedTypeReference, id);
    //         //ResponseDto<UserPostDto> usersResponse = response.getBody();            
    //         UserPostDto usersResponse = modelMapper.map(response.getBody().getResponse(), UserPostDto.class);
    //         return usersResponse;

    //     } catch (HttpClientErrorException ex) {
    //         throw ex;
    //     }
    // }

    @Override
    public UserPostDto delete(int id) {
        try {
            ParameterizedTypeReference<ResponseDto<UserPostDto>> parameterizedTypeReference = new ParameterizedTypeReference<ResponseDto<UserPostDto>>() {
            };
            ResponseEntity<ResponseDto<UserPostDto>> response = restTemplate.exchange(POSTS_URL + "/delete/{id}",
                    HttpMethod.DELETE, null, parameterizedTypeReference, id);
            ResponseDto<UserPostDto> userResponse = response.getBody();
            return userResponse.getResponse();
        } catch (HttpClientErrorException ex) {
            throw ex;
        }
    }

    @Override
    public UserPostDto update(UserPostDto post, int id) {
        try {
            ParameterizedTypeReference<ResponseDto<UserPostDto>> parameterizedTypeReference = new ParameterizedTypeReference<ResponseDto<UserPostDto>>() {
            };
            HttpEntity<UserPostDto> request = new HttpEntity<>(post);
            ResponseEntity<ResponseDto<UserPostDto>> response = restTemplate.exchange(POSTS_URL + "/update/{id}",
                    HttpMethod.PUT, request, parameterizedTypeReference, id);
            ResponseDto<UserPostDto> userResponse = response.getBody();
            return userResponse.getResponse();
        } catch (HttpClientErrorException ex) {
            throw ex;
        }
    }

    @Override
    public UserPostDto add(UserPostDto post) {
        try {
            ParameterizedTypeReference<ResponseDto<UserPostDto>> parameterizedTypeReference = new ParameterizedTypeReference<ResponseDto<UserPostDto>>() {
            };
            HttpEntity<UserPostDto> request = new HttpEntity<>(post);
            ResponseEntity<ResponseDto<UserPostDto>> response = restTemplate.exchange(POSTS_URL + "/add",
                    HttpMethod.PUT, request, parameterizedTypeReference);
            ResponseDto<UserPostDto> userResponse = response.getBody();
            return userResponse.getResponse();
        } catch (HttpClientErrorException ex) {
            throw ex;
        }
    }

}
