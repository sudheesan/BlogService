package edu.mum.cs544.BlogService.dtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDto {
    private Integer id;
    private String username;
    private String firstname;
    private String lastname;
    private boolean isActive;
}
