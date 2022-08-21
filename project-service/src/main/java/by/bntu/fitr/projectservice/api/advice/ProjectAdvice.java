package by.bntu.fitr.projectservice.api.advice;

import by.bntu.fitr.projectservice.api.exception.AlreadyExistsException;
import by.bntu.fitr.projectservice.api.exception.NotFoundException;
import by.bntu.fitr.projectservice.api.jwt.JWTAuthenticationException;
import by.bntu.fitr.projectservice.api.model.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<HttpResponse> handleNotFoundException(NotFoundException e) {
        HttpResponse httpResponse = new HttpResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(httpResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<HttpResponse> handleAlreadyExistsException(AlreadyExistsException e) {
        HttpResponse httpResponse = new HttpResponse(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>(httpResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(JWTAuthenticationException.class)
    public ResponseEntity<HttpResponse> handleJWTAuthenticationException(JWTAuthenticationException e) {
        HttpResponse httpResponse = new HttpResponse(e.getMessage(), HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(httpResponse,HttpStatus.FORBIDDEN);
    }
}
