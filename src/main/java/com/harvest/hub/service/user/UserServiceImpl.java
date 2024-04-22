package com.harvest.hub.service.user;

import com.harvest.hub.constant.enums.UserType;
import com.harvest.hub.dao.LoginDao;
import com.harvest.hub.dao.RegisterDao;
import com.harvest.hub.dto.AuthResponse;
import com.harvest.hub.dto.UserDto;
import com.harvest.hub.entity.User;
import com.harvest.hub.repository.UserRepository;
import com.harvest.hub.security.UserDetailsImpl;
import com.harvest.hub.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public AuthResponse login(LoginDao loginDao) {
        Authentication authenticate = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDao.getEmail(),
                        loginDao.getPassword()
                )
        );

        if (authenticate.isAuthenticated()) {
            User user = this.userRepository.findByActiveTrueAndEmail(loginDao.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid username or password."));
            log.info(loginDao.getEmail() + ", User logged in Successfully");

            return AuthResponse.builder()
                    .accessToken(this.jwtService.generateToken(new UserDetailsImpl(user)))
                    .refreshToken(this.jwtService.generateRefreshToken(new UserDetailsImpl(user)))
                    .user(new UserDto(user))
                    .build();
        } else {
            throw new RuntimeException("Invalid username or password.");
        }
    }

    @Override
    public void register(RegisterDao registerDao) {
        this.userRepository.findByActiveTrueAndEmail(registerDao.getEmail()).ifPresentOrElse(
                user -> {
                    throw new IllegalArgumentException("Email already in use.");
                }, () -> {
                    this.userRepository.save(
                            User.builder()
                                    .firstName(registerDao.getFirstName())
                                    .lastName(registerDao.getLastName())
                                    .email(registerDao.getEmail())
                                    .password(passwordEncoder.encode(registerDao.getPassword()))
                                    .photo(registerDao.getPhoto())
                                    .mobileNo(registerDao.getMobileNo())
                                    .userType(UserType.CUSTOMER)
                                    .active(Boolean.TRUE)
                                    .createdBy("SYSTEM")
                                    .createdDate(LocalDateTime.now())
                                    .build()
                    );
                }
        );
    }

    @Override
    public UserDto me(Principal principal) {
        UserDetailsImpl userDetails = (UserDetailsImpl)((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        return this.userRepository.findByActiveTrueAndEmail(userDetails.getUsername())
                .map(UserDto::new)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return this.userRepository.findByActiveTrue()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id, Principal principal) {
        UserDetailsImpl userDetails = (UserDetailsImpl)((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        this.userRepository.findByActiveTrueAndId(id).ifPresentOrElse(
                user -> {
                    user.setActive(Boolean.FALSE);
                    user.setUpdatedBy(userDetails.getName());
                    user.setUpdatedDate(LocalDateTime.now());
                    this.userRepository.save(user);
                }, () -> {
                    throw new IllegalArgumentException("No User Found");
                }
        );
    }

    @Override
    public void createUser(RegisterDao registerDao, Principal principal) {
        UserDetailsImpl userDetails = (UserDetailsImpl)((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        this.userRepository.findByActiveTrueAndEmail(registerDao.getEmail()).ifPresentOrElse(
                user -> {
                    throw new IllegalArgumentException("Email already in use.");
                }, () -> {
                    this.userRepository.save(
                            User.builder()
                                    .firstName(registerDao.getFirstName())
                                    .lastName(registerDao.getLastName())
                                    .email(registerDao.getEmail())
                                    .password(passwordEncoder.encode(registerDao.getPassword()))
                                    .photo(registerDao.getPhoto())
                                    .mobileNo(registerDao.getMobileNo())
                                    .userType(registerDao.getUserType())
                                    .active(Boolean.TRUE)
                                    .createdBy(userDetails.getName())
                                    .createdDate(LocalDateTime.now())
                                    .build()
                    );
                }
        );
    }

}
