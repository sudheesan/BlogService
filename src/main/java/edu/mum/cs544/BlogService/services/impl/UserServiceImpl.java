package edu.mum.cs544.BlogService.services.impl;

import edu.mum.cs544.BlogService.dtos.LoginRequest;
import edu.mum.cs544.BlogService.dtos.LoginResponse;
import edu.mum.cs544.BlogService.dtos.ResponseDto;
import edu.mum.cs544.BlogService.helpers.JwtHelper;
import edu.mum.cs544.BlogService.models.User;
import edu.mum.cs544.BlogService.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final RestTemplate restTemplate;

    private final static String USERS_URL = "http://localhost:9090/users";


    @Override
    public User loadUserByUsername(String username) {
        ParameterizedTypeReference<ResponseDto<User>> parameterizedTypeReference = new ParameterizedTypeReference<ResponseDto<User>>() {
        };
        ResponseEntity<ResponseDto<User>> response =
                restTemplate.exchange(USERS_URL + "/getUserByUsername?username={username}", HttpMethod.GET, null, parameterizedTypeReference, username);
        ResponseDto<User> user = response.getBody();
        System.out.println("The data" + user.toString());
        return user.getResponse();
    }

}
