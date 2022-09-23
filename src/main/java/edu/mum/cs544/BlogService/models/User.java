package edu.mum.cs544.BlogService.models;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class User {
    private Integer id;
    @NotNull
    private String username;
    @NotNull
    private String firstname;
    @NotNull
    private String password;
    private String lastname;
    private boolean isActive;
}
