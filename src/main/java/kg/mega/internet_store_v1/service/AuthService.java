package kg.mega.internet_store_v1.service;

import kg.mega.internet_store_v1.models.dto.AuthRequestDto;
import kg.mega.internet_store_v1.models.dto.AuthResponseDto;
import kg.mega.internet_store_v1.models.dto.RegistrationRequestDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> authenticate(AuthRequestDto authRequestDto);
    ResponseEntity<?> registrateNewUser(RegistrationRequestDto registrationRequestDto);
}
