package edu.mum.cs544.BlogService.services;

import edu.mum.cs544.BlogService.dtos.ResponseDto;
import edu.mum.cs544.BlogService.models.User;

public interface UserService {
    public ResponseDto<String> save(User user);
}
