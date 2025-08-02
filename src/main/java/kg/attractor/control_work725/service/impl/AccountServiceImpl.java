package kg.attractor.control_work725.service.impl;

import kg.attractor.control_work725.dao.AccountDao;
import kg.attractor.control_work725.dao.UserDao;
import kg.attractor.control_work725.dto.AccountCreateDto;
import kg.attractor.control_work725.model.Account;
import kg.attractor.control_work725.model.User;
import kg.attractor.control_work725.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;
    private final UserDao userDao;

    @Override
    public void createAccount(AccountCreateDto accountDto, User user) {
        List<Account> userAccounts = accountDao.findByUserId(user.getId());

        if (userAccounts.size() >= 3) {
            throw new IllegalStateException("Нельзя создать более 3 счетов.");
        }

        boolean existsForCurrency = userAccounts.stream()
                .anyMatch(a -> a.getCurrency().equalsIgnoreCase(accountDto.getCurrency()));
        if (existsForCurrency) {
            throw new IllegalStateException("Счёт с такой валютой уже существует.");
        }

        Account account = new Account();
        account.setUserId(user.getId());
        account.setCurrency(accountDto.getCurrency());
        account.setBalance(0.0);

        accountDao.save(account);
    }

    @Override
    public double getBalance(Long accountId, User user) {
        Account account = accountDao.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Счёт не найден."));

        if (!account.getUserId().equals(user.getId())) {
            throw new SecurityException("Вы не имеете доступа к этому счёту.");
        }

        return account.getBalance();
    }

    @Override
    public void topUpAccount(Long accountId, double amount, User user) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма должна быть положительной.");
        }

        Account account = accountDao.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Счёт не найден."));

        if (!account.getUserId().equals(user.getId())) {
            throw new SecurityException("Вы не имеете доступа к этому счёту.");
        }

        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
        accountDao.update(account);
    }

    @Override
    public List<Account> getAccounts(User user) {
        return accountDao.findByUserId(user.getId());
    }
}