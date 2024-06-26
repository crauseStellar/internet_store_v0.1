package kg.mega.internet_store_v1.service.impl;

import kg.mega.internet_store_v1.mapper.UserMapper;
import kg.mega.internet_store_v1.models.Basket;
import kg.mega.internet_store_v1.models.User;
import kg.mega.internet_store_v1.models.dto.ActivateUserDto;
import kg.mega.internet_store_v1.models.dto.ResponseDto;
import kg.mega.internet_store_v1.models.dto.UserDto;
import kg.mega.internet_store_v1.repository.UserRepo;
import kg.mega.internet_store_v1.service.BasketService;
import kg.mega.internet_store_v1.service.RoleService;
import kg.mega.internet_store_v1.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final BasketService basketService;
    private final UserMapper userMapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    @Override
    public User saveUser(User user) {
        User newUser = null;
        if (findByEmail(user.getEmail()).isPresent()) {
            System.out.println("User already exists");
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Set.of(roleService.findByName("USER")));
            newUser = userRepo.save(user);
            basketService.save(new Basket(findByEmail(user.getEmail()).get()));
            sendMail(user.getEmail(),"Save user","User"+user.getFio()+" has been successfully saved");
        }
        return newUser;
    }

    @Override
    public UserDto findById(Long id) {
        return userMapper.toDto(userRepo.findById(id).orElse(null));
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User updateUser(User user) {
        sendMail(user.getEmail(),"Update user","User"+user.getFio()+" successfully updated!");
        return userRepo.save(user);
    }

    @Override
    public String delete(User user) {
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User update(User user) {
        return userRepo.save(user);
    }

    @Override
    public User getById(Long userId) {
        return userRepo.findById(userId).get();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    private void sendMail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        log.info("Sending email to {} with subject {}", to,subject);
        mailSender.send(message);
    }

    @Override
    public ResponseEntity<?> activateUser(ActivateUserDto activateUserDto) {
        Optional<User> optionalUser = findByEmail(activateUserDto.getEmail());
        if (optionalUser.isEmpty()) {
            log.error("There is no optionalUser with email " + activateUserDto.getEmail());
            return ResponseEntity.status(487).body("There is no optionalUser with email"+activateUserDto.getEmail());
        }
        User user = optionalUser.get();
        if (user.isActive()){
            return ResponseEntity.status(486).body("User is already activated");
        }
        log.info("Activating user by email: " + activateUserDto.getEmail());
        if (user.getActivationCode().equals(activateUserDto.getActivationCode())){
            user.setActive(true);
            userRepo.save(user);
            log.info("User activated");
            return ResponseEntity.ok("User activated successfully!");
        }
        log.error("Activating code is incorrect! ");
        return ResponseEntity.status(488).body("Activating code is incorrect! ");
    }

    private void test(ResponseDto responseDto) {
        responseDto.getUser().setUsername("sdafsdf");
        responseDto.getGood().get(0);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> container = userRepo.findByUsername(username);
        if (container.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        User user = container.get();
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList()
        );
    }
//    private PasswordEncoder passwordEncoder() {
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        return encoder;
//    }
}
