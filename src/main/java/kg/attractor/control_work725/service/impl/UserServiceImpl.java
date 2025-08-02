package kg.attractor.control_work725.service.impl;

import kg.attractor.control_work725.dao.AccountDao;
import kg.attractor.control_work725.dao.UserDao;
import kg.attractor.control_work725.dto.UserRegisterDto;
import kg.attractor.control_work725.model.Account;
import kg.attractor.control_work725.model.User;
import kg.attractor.control_work725.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final AccountDao accountDao;

    @Override
    public void registerUser(UserRegisterDto userDto) {
        if (userDao.findByPhoneNumber(userDto.getPhoneNumber()).isPresent()) {
            throw new IllegalArgumentException("Пользователь с таким номером телефона уже существует.");
        }

        User user = new User();
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setRole("USER");
        user.setBlocked(false);

        userDao.save(user);

        Account defaultAccount = new Account();
        defaultAccount.setUserId(user.getId());
        defaultAccount.setCurrency("KGS");
        defaultAccount.setBalance(0.0);
        accountDao.save(defaultAccount);
    }
}
