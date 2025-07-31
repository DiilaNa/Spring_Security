package lk.ijse.project.backend.service;

import lk.ijse.project.backend.dto.AuthDto;
import lk.ijse.project.backend.dto.AuthResponseDto;
import lk.ijse.project.backend.dto.RegisterDto;
import lk.ijse.project.backend.entity.User;
import lk.ijse.project.backend.repository.UserRepository;
import lk.ijse.project.backend.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public AuthResponseDto authenticate(AuthDto authDto) {


       User user =  userRepository.findByUserName(authDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
       if (!passwordEncoder.matches(
               authDto.getPassword(),
               user.getPassword()))
       {
           throw new BadCredentialsException("Bad credentials");
       }
       //generate token
       String token = jwtUtil.generateToken(authDto.getUsername());
        return new AuthResponseDto(token);
    }

    public String Register(RegisterDto registerDto) {
        if (userRepository.findByUserName(registerDto.getUsername()).isPresent()){
            throw new RuntimeException("Username already exists");
        }

        User user = User.builder()
                .userName(registerDto.getUsername())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .build();
        userRepository.save(user);
        return "User Register SuccessFully";
    }
}
