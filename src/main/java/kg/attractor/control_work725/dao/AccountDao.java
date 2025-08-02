package kg.attractor.control_work725.dao;

import kg.attractor.control_work725.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountDao {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Account> accountRowMapper = (rs, rowNum) -> {
        Account account = new Account();
        account.setId(rs.getLong("id"));
        account.setUserId(rs.getLong("user_id"));
        account.setCurrency(rs.getString("currency"));
        account.setBalance(rs.getDouble("balance"));
        return account;
    };

    public Optional<Account> findByUserIdAndCurrency(Long userId, String currency) {
        String sql = "SELECT * FROM accounts WHERE user_id = ? AND currency = ?";
        List<Account> accounts = jdbcTemplate.query(sql, accountRowMapper, userId, currency);
        return accounts.stream().findFirst();
    }

    public void save(Account account) {
        String sql = "INSERT INTO accounts (user_id, currency, balance) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, account.getUserId(), account.getCurrency(), account.getBalance());
    }
}
