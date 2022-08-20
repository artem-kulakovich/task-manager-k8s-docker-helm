package by.bntu.fitr.projectservice.advice;

import by.bntu.fitr.projectservice.exception.AlreadyExistsException;
import by.bntu.fitr.projectservice.exception.NotFoundException;
import by.bntu.fitr.projectservice.model.HttpResponse;
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
}
