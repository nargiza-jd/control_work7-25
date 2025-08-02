package kg.attractor.control_work725.service.impl;

import kg.attractor.control_work725.dao.AccountDao;
import kg.attractor.control_work725.dao.UserDao;
import kg.attractor.control_work725.dto.AccountCreateDto;
import kg.attractor.control_work725.model.Account;
import kg.attractor.control_work725.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao;
    private final UserDao userDao;

    @Override
    public void createAccount(AccountCreateDto accountDto) {
        Long currentUserId = 1L;

        Account account = new Account();
        account.setUserId(currentUserId);
        account.setCurrency(accountDto.getCurrency());
        account.setBalance(0.0);

        accountDao.save(account);
    }

    @Override
    public double getAccountBalance() {
        // TODO: Реализовать логику получения баланса
        return 0.0;
    }

    @Override
    public void topUpAccount(Double amount) {
        // TODO: Реализовать логику пополнения счета
    }

    @Override
    public List<Account> getAccounts() {
        // TODO: Реализовать логику получения списка счетов для текущего пользователя
        return Collections.emptyList();
    }
}
