package edu.mum.cs544.BlogService.controllers;

import edu.mum.cs544.BlogService.dtos.ResponseDto;
import edu.mum.cs544.BlogService.dtos.UserDto;
import edu.mum.cs544.BlogService.models.User;
import edu.mum.cs544.BlogService.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blogs/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private ModelMapper modelMapper;

    @GetMapping("/getUserByUsername")
    public ResponseDto<UserDto> getUserByUserName(@RequestParam String username) {
        User user = userService.loadUserByUsername(username);
        return new ResponseDto<>("User", false, modelMapper.map(user, UserDto.class), null);
    }
}
