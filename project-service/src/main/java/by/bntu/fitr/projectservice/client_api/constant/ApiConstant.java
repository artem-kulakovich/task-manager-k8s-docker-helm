package by.bntu.fitr.projectservice.client_api.constant;

public interface ApiConstant {

    interface AuthenticationService {
        String DNS = "http://localhost:8080";
        String API_VERSION = "/api/v1/";
        String SERVICE_NAME = "authentication-service";
        String GET_USER_BY_EMAIL_API = DNS + API_VERSION + SERVICE_NAME + "/users/find-by-email";
    }

}
