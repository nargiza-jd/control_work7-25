package kg.attractor.control_work725.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegisterDto {
    @NotBlank(message = "Номер телефона не может быть пустым.")
    @Pattern(regexp = "^(996)\\d{9}$", message = "Номер телефона должен начинаться с '996' и иметь 12 цифр.")
    private String phoneNumber;

    @NotBlank(message = "Имя пользователя не может быть пустым.")
    @Size(min = 2, max = 50, message = "Имя пользователя должно быть от 2 до 50 символов.")
    private String name;

    @NotBlank(message = "Пароль не может быть пустым.")
    @Size(min = 6, message = "Пароль должен быть не менее 6 символов.")
    private String password;
}
