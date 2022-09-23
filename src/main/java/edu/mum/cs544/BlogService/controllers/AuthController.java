package edu.mum.cs544.BlogService.controllers;

import edu.mum.cs544.BlogService.dtos.LoginRequest;
import edu.mum.cs544.BlogService.dtos.LoginResponse;
import edu.mum.cs544.BlogService.dtos.ResponseDto;
import edu.mum.cs544.BlogService.models.User;
import edu.mum.cs544.BlogService.services.AuthService;
import edu.mum.cs544.BlogService.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/blogs/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<ResponseDto<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
        var loginResponse = authService.login(loginRequest);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto<String>> signUp(@RequestBody User user){
        var response = authService.save(user);
        return  ResponseEntity.ok().body(response);
    }
}
