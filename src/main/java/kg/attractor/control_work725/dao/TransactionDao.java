package kg.attractor.control_work725.dao;

import kg.attractor.control_work725.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TransactionDao {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Transaction> transactionRowMapper = (rs, rowNum) -> {
        Transaction transaction = new Transaction();
        transaction.setId(rs.getLong("id"));
        transaction.setFromAccountId(rs.getLong("from_account_id"));
        transaction.setToAccountId(rs.getLong("to_account_id"));
        transaction.setAmount(rs.getDouble("amount"));
        transaction.setCurrency(rs.getString("currency"));
        transaction.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
        transaction.setStatus(rs.getString("status"));
        transaction.setAdminId(rs.getLong("admin_id"));
        return transaction;
    };

    public Optional<Transaction> findById(Long id) {
        String sql = "SELECT * FROM transactions WHERE id = ?";
        List<Transaction> transactions = jdbcTemplate.query(sql, transactionRowMapper, id);
        return transactions.stream().findFirst();
    }

    public List<Transaction> findByStatus(String status) {
        String sql = "SELECT * FROM transactions WHERE status = ?";
        return jdbcTemplate.query(sql, transactionRowMapper, status);
    }

    public void save(Transaction transaction) {
        String sql = "INSERT INTO transactions (from_account_id, to_account_id, amount, currency, timestamp, status, admin_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, transaction.getFromAccountId(), transaction.getToAccountId(), transaction.getAmount(), transaction.getCurrency(), transaction.getTimestamp(), transaction.getStatus(), transaction.getAdminId());
    }

    public void update(Transaction transaction) {
        String sql = "UPDATE transactions SET status = ?, admin_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, transaction.getStatus(), transaction.getAdminId(), transaction.getId());
    }
}