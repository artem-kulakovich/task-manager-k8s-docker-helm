package by.bntu.fitr.authenticationservice.model;


import lombok.*;
import org.springframework.http.HttpStatus;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HttpResponse {
    private String errorMsg;
    private HttpStatus httpCode;

    public HttpResponse(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public HttpResponse(HttpStatus httpCode) {
        this.httpCode = httpCode;
    }
}
