package kg.mega.internet_store_v1.service;

import kg.mega.internet_store_v1.models.dto.AuthRequestDto;
import kg.mega.internet_store_v1.models.dto.AuthResponseDto;
import kg.mega.internet_store_v1.models.dto.RegistrationRequestDto;

public interface AuthService {
    AuthResponseDto authenticate(AuthRequestDto authRequestDto);
    AuthResponseDto registrateNewUser(RegistrationRequestDto registrationRequestDto);
}
