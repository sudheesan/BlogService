package edu.mum.cs544.BlogService.services.impl;

import edu.mum.cs544.BlogService.dtos.ResponseDto;
import edu.mum.cs544.BlogService.dtos.UserDto;
import edu.mum.cs544.BlogService.dtos.UserPostDto;
import edu.mum.cs544.BlogService.models.Post;
import edu.mum.cs544.BlogService.services.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Transient;
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

    private final static String POSTS_URL = "http://localhost:9090/api/v1/posts";
    @Override
    public List<UserPostDto> getAllPostsById(int id) {
        try {
            ParameterizedTypeReference<List<Post>> parameterizedTypeReference = new ParameterizedTypeReference<List<Post>>() {
            };
            ResponseEntity<List<Post>> response =
                    restTemplate.exchange(POSTS_URL, HttpMethod.GET, null, parameterizedTypeReference, id);
            List<Post> postsForId = response.getBody();
            return postsForId.stream().map(p -> modelMapper.map(p, UserPostDto.class)).collect(Collectors.toList());
        } catch (HttpClientErrorException ex) {
            throw ex;
        }
    }
}
