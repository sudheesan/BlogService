package edu.mum.cs544.BlogService.dtos;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class UserPostDto {
    private int id;
    private String title;
    private Date dateLastUpdated;
    private String text;
}
