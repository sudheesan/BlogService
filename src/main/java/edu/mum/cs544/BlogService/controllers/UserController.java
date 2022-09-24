package edu.mum.cs544.BlogService.controllers;

import edu.mum.cs544.BlogService.dtos.ResponseDto;
import edu.mum.cs544.BlogService.dtos.UserDto;
import edu.mum.cs544.BlogService.dtos.UserPostDto;
import edu.mum.cs544.BlogService.models.User;
import edu.mum.cs544.BlogService.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blogs/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private ModelMapper modelMapper;

    @GetMapping("/getUserByUsername")
    public ResponseEntity<ResponseDto<UserDto>> getUserByUserName(@RequestParam String username) {
        User user = userService.loadUserByUsername(username);
        return ResponseEntity.ok().body(new ResponseDto<UserDto>("User", false, modelMapper.map(user, UserDto.class), null)) ;
    }

    @GetMapping()
    public ResponseEntity<ResponseDto<List<UserDto>>> getAll() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok().body( new ResponseDto<>("Users", false, users, null));
    }
    @GetMapping("/{id}")
    public ResponseDto<UserDto> getUser(@PathVariable int id) {
        UserDto user = userService.getUserById(id);
        return new ResponseDto<>("User", false, user, null);
    }

    @PutMapping()
    public ResponseEntity<ResponseDto<UserDto>> updateUser(@RequestBody UserDto userDto) {
        UserDto user = userService.update(userDto);
        return ResponseEntity.ok().body(new ResponseDto<>("Updated user", false, user, null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<UserDto>> deleteUser(@PathVariable int id) {
        UserDto user = userService.deleteUser(id);
        return ResponseEntity.ok().body(new ResponseDto<>("Deleted user", false, user, null));
    }

    @GetMapping("/posts")
    public ResponseEntity<ResponseDto<List<UserPostDto>>> getAllPosts(

    ) {
        List<UserPostDto> posts = userService.getAllPosts();
        return ResponseEntity.ok().body(new ResponseDto<>("Users post", false, posts, null));
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<ResponseDto<List<UserPostDto>>> getAllPostsById(@PathVariable int id) {
        List<UserPostDto> posts = userService.getAllPostsByUserId(id);
        return ResponseEntity.ok().body(new ResponseDto<>("User posts", false, posts, null));
    }
}
