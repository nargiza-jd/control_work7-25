package kg.attractor.control_work725.service.impl;

import kg.attractor.control_work725.dao.AccountDao;
import kg.attractor.control_work725.dao.UserDao;
import kg.attractor.control_work725.dto.AccountCreateDto;
import kg.attractor.control_work725.model.Account;
import kg.attractor.control_work725.model.User;
import kg.attractor.control_work725.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao;
    private final UserDao userDao;

    @Override
    public void createAccount(AccountCreateDto accountDto, User user) {
        Account account = new Account();
        account.setUserId(user.getId());
        account.setCurrency(accountDto.getCurrency());
        account.setBalance(0.0);

        accountDao.save(account);
    }
}
