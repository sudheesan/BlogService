package edu.mum.cs544.BlogService.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class UserPostDto {
    private int id;
    private String title;
    private Date dateLastUpdated;
    private String text;
}
