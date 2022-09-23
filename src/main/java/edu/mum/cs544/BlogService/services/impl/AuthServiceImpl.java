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
import edu.mum.cs544.BlogService.services.AuthService;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RestTemplate restTemplate;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelper jwtHelper;

    private final UserService userService;

    private final static String USERS_URL = "http://localhost:9090/users";

    @Override
    public ResponseDto<String> save(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            ParameterizedTypeReference<ResponseDto<String>> parameterizedTypeReference = new ParameterizedTypeReference<ResponseDto<String>>() {
            };
            HttpEntity<User> request = new HttpEntity<>(user);
            ResponseEntity<ResponseDto<String>> response =
                    restTemplate.exchange(USERS_URL, HttpMethod.POST, request, parameterizedTypeReference);
            return response.getBody();
        } catch (HttpClientErrorException ex) {
            throw ex;
        }

    }


    @Override
    public ResponseDto<LoginResponse> login(LoginRequest loginRequest) {
        try {
            var result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                            loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw e;
        }

        var userDetails = userService.loadUserByUsername(loginRequest.getUsername());
        Map<String, Object> claimMap = new HashMap<>();
        claimMap.put("user_id", Integer.toString(userDetails.getId()));
        final String accessToken = jwtHelper.generateToken(loginRequest.getUsername(), claimMap);
        final String refreshToken = jwtHelper.generateRefreshToken(loginRequest.getUsername());
        var loginResponse = new LoginResponse(accessToken, refreshToken);
        return new ResponseDto<>("Successfully logged in", false, loginResponse, null);
    }
}
