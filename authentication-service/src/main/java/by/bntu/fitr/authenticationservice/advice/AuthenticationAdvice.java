package by.bntu.fitr.authenticationservice.advice;


import by.bntu.fitr.authenticationservice.exception.*;
import by.bntu.fitr.authenticationservice.model.HttpResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AuthenticationAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<HttpResponse> handleLoginException(LoginException e) {
        HttpResponse httpResponse = new HttpResponse(e.getMessage(), HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(httpResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<HttpResponse> handleNotFoundException(NotFoundException e) {
        HttpResponse httpResponse = new HttpResponse(HttpStatus.NOT_FOUND);
        if (e instanceof UserNotFoundException) {
            httpResponse.setErrorMsg(e.getMessage());
        } else if (e instanceof RoleNotFoundException) {
            httpResponse.setErrorMsg(e.getMessage());
        }

        return new ResponseEntity<>(httpResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<HttpResponse> handlePasswordMismatchException(PasswordMismatchException e) {
        HttpResponse httpResponse = new HttpResponse(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>(httpResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<HttpResponse> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        HttpResponse httpResponse = new HttpResponse(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>(httpResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(
                (error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    errors.put(fieldName, message);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
