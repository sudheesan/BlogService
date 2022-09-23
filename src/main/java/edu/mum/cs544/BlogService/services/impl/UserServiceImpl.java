package edu.mum.cs544.BlogService.services.impl;

import edu.mum.cs544.BlogService.dtos.ResponseDto;
import edu.mum.cs544.BlogService.dtos.UserDto;
import edu.mum.cs544.BlogService.models.User;
import edu.mum.cs544.BlogService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;

    private final static String USERS_URL = "http://localhost:9090/users";
    @Override
    public ResponseDto<String> save(User user) {
        try {
            ParameterizedTypeReference<ResponseDto<String>> parameterizedTypeReference = new ParameterizedTypeReference<ResponseDto<String>>() {};
            HttpEntity<User> request = new HttpEntity<>(user);
            ResponseEntity<ResponseDto<String>> response =
                    restTemplate.exchange(USERS_URL, HttpMethod.POST, request, parameterizedTypeReference);
            return response.getBody();
        }catch (HttpClientErrorException ex){
            throw ex;
        }

    }
}
