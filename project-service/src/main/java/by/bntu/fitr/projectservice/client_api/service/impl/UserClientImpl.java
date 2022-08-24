package by.bntu.fitr.projectservice.client_api.service.impl;

import by.bntu.fitr.projectservice.client_api.constant.ApiCommonConstant;
import by.bntu.fitr.projectservice.client_api.constant.ApiConstant;
import by.bntu.fitr.projectservice.client_api.dto.response.UserClientResponseDTO;
import by.bntu.fitr.projectservice.client_api.factory.RestTemplateUserFactory;
import by.bntu.fitr.projectservice.client_api.service.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserClientImpl implements UserClient {
    private final RestTemplateUserFactory restTemplateUserFactory;

    @Autowired
    public UserClientImpl(RestTemplateUserFactory restTemplateUserFactory) {
        this.restTemplateUserFactory = restTemplateUserFactory;
    }

    @Override
    public UserClientResponseDTO getUserByEmail(String email) {
        String path = ApiConstant.AuthenticationService.GET_USER_BY_EMAIL_API;
        Map<String, String> params = Collections.singletonMap(ApiCommonConstant.EMAIL_ATTR, email);

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(path)
                .queryParam("email", "{email}")
                .encode()
                .toUriString();

        ResponseEntity<UserClientResponseDTO> user = restTemplateUserFactory.getUserClientResponseDTOWithGetMethod(
                urlTemplate,
                params
        );
        return user.getBody();
    }
}
