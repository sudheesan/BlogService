package edu.mum.cs544.BlogService.exceptions.handlers;

import edu.mum.cs544.BlogService.dtos.ResponseDto;
import edu.mum.cs544.BlogService.exceptions.UsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ControllerExceptionHandlers {

    @ExceptionHandler
    public ResponseEntity<ResponseDto<String>> exception(HttpClientErrorException exception) {
        ResponseDto<String> responseDto = new ResponseDto<>(null, true, null, exception.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDto<String>> exception(UsernameAlreadyExistsException exception) {
        ResponseDto<String> responseDto = new ResponseDto<>(null, true, null, exception.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDto<String>> exception(BadCredentialsException exception) {
        ResponseDto<String> responseDto = new ResponseDto<>(null, true, null, exception.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
    }
}
