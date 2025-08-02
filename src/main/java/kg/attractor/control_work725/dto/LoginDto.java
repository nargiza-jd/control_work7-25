package kg.attractor.control_work725.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDto {
    private String phoneNumber;
    private String password;
}
