package by.bntu.fitr.projectservice.client_api.factory;

import by.bntu.fitr.projectservice.client_api.dto.response.UserClientResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class RestTemplateUserFactory {
    private final RestTemplate restTemplate;

    @Autowired
    public RestTemplateUserFactory(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<UserClientResponseDTO> getUserClientResponseDTOWithGetMethod(String path, Map<String, String> params) {
        return restTemplate.getForEntity(path, UserClientResponseDTO.class, params);
    }

    public ResponseEntity<UserClientResponseDTO> getUserClientResponseDTOWithGetMethod(String path) {
        return restTemplate.getForEntity(path, UserClientResponseDTO.class);
    }
}
