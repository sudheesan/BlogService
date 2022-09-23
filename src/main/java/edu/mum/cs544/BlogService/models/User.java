package edu.mum.cs544.BlogService.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    private Integer id;
    private String username;
    private String firstname;
    private String password;
    private String lastname;
    private boolean isActive;
}
