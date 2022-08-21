package by.bntu.fitr.projectservice.client_api.service;

import by.bntu.fitr.projectservice.client_api.dto.response.UserClientResponseDTO;


public interface UserClient {

    UserClientResponseDTO getUserByEmail(String email);
}
