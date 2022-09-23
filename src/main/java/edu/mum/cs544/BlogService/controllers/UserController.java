package edu.mum.cs544.BlogService.controllers;

import edu.mum.cs544.BlogService.dtos.ResponseDto;
import edu.mum.cs544.BlogService.models.User;
import edu.mum.cs544.BlogService.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseDto> signUp(@RequestBody User user){
        ResponseDto<String> response = userService.save(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
