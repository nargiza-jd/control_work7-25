package kg.attractor.control_work725.service.impl;

import kg.attractor.control_work725.dao.UserDao;
import kg.attractor.control_work725.model.User;
import kg.attractor.control_work725.service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userDao.findByPhoneNumber(username);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User with phone number " + username + " not found.");
        }

        return userOptional.get();
    }
}
