package kg.attractor.control_work725.service;

import kg.attractor.control_work725.dto.AccountCreateDto;
import kg.attractor.control_work725.model.User;

public interface AccountService {
    void createAccount(AccountCreateDto accountDto, User user);
}
