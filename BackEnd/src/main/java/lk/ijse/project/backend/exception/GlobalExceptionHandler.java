package lk.ijse.project.backend.exception;

import io.jsonwebtoken.ExpiredJwtException;
import lk.ijse.project.backend.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse handleUserNameNotFoundException(UsernameNotFoundException e){
        return new ApiResponse(404, "User not found", null );
    }
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleBadCredentialsException(BadCredentialsException e){
        return new ApiResponse(400, "Bad credentials", null );
    }

//    handle JWT token expire errors
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse handleJWTTokenExpireException(Exception e){
        return new ApiResponse(401, "JWT token expired", null );
    }
//    handle all exceptions
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse handleAllException(RuntimeException e){
        return new ApiResponse(500, "Internal Server Error", e.getMessage() );
    }
}
