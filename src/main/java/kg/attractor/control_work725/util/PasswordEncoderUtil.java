package kg.attractor.control_work725.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("USER пароль (12345): " + encoder.encode("12345"));
        System.out.println("ADMIN пароль (qwerty): " + encoder.encode("qwerty"));
    }
}