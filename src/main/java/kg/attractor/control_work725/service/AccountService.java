package kg.attractor.control_work725.service;

import kg.attractor.control_work725.dto.AccountCreateDto;
import kg.attractor.control_work725.model.User;
import kg.attractor.control_work725.model.Account;

import java.util.List;

public interface AccountService {
    void createAccount(AccountCreateDto accountDto, User user);

    double getBalance(Long accountId, User user);

    void topUpAccount(Long accountId, double amount, User user);

    List<Account> getAccounts(User user);
}