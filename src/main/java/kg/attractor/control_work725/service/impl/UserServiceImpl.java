package kg.attractor.control_work725.service.impl;

import kg.attractor.control_work725.dao.UserDao;
import kg.attractor.control_work725.dto.UserRegisterDto;
import kg.attractor.control_work725.model.User;
import kg.attractor.control_work725.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserRegisterDto userDto) {
        Optional<User> existingUser = userDao.findByPhoneNumber(userDto.getPhoneNumber());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Пользователь с таким номером телефона уже существует.");
        }

        User newUser = new User();
        newUser.setPhoneNumber(userDto.getPhoneNumber());
        newUser.setName(userDto.getName());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setRole("ROLE_USER");
        newUser.setBlocked(false);

        userDao.save(newUser);
    }
}
