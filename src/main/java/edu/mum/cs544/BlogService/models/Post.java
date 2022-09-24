package edu.mum.cs544.BlogService.models;

import lombok.Data;

import java.util.Date;
@Data
public class Post {
    private int id;
    private String title;
    private Date dateLastUpdated;
    private String text;
    private int userId;
}
