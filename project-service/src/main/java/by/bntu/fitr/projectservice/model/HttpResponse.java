package by.bntu.fitr.projectservice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
