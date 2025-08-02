package lk.ijse.project.backend.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String userName;
    private String password;
    private String role;
}
