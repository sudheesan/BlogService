package edu.mum.cs544.BlogService.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {
    private String message;
    private boolean hasError = false;
    private T response = null;
    private String errorMessage;
}
