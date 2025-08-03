package lk.ijse.project.backend.controller;

import lk.ijse.project.backend.dto.ApiResponse;
import lk.ijse.project.backend.dto.AuthDto;
import lk.ijse.project.backend.dto.RegisterDto;
import lk.ijse.project.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(
            @RequestBody RegisterDto registerDto){
        return ResponseEntity.ok(
                new ApiResponse(
                        200,
                        "User Registered Successfully",
                        authService.Register(registerDto)
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody AuthDto authDto){
        return ResponseEntity.ok(new ApiResponse(
                200,"ok", authService.authenticate(authDto)
        ));
    }
}
