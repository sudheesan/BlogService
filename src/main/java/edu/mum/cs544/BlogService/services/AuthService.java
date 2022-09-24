package edu.mum.cs544.BlogService.services;

import edu.mum.cs544.BlogService.dtos.LoginRequest;
import edu.mum.cs544.BlogService.dtos.LoginResponse;
import edu.mum.cs544.BlogService.dtos.ResponseDto;
import edu.mum.cs544.BlogService.models.User;

public interface AuthService {
    public ResponseDto<String> save(User user);
    public ResponseDto<LoginResponse> login(LoginRequest loginRequest);
}
