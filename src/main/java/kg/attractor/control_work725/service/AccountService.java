package kg.attractor.control_work725.service;

import kg.attractor.control_work725.dto.AccountCreateDto;
import kg.attractor.control_work725.model.Account;

import java.util.List;

public interface AccountService {
    void createAccount(AccountCreateDto accountDto);
    double getAccountBalance();
    void topUpAccount(Double amount);
    List<Account> getAccounts();
}
