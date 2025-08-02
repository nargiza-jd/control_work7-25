package kg.attractor.control_work725.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String phoneNumber;
    private String name;
    private String password;
    private String role;
    private boolean isBlocked;
}
