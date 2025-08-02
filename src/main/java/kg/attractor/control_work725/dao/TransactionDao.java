package kg.attractor.control_work725.dao;

import kg.attractor.control_work725.model.Transaction;
import kg.attractor.control_work725.model.TransactionStatus;
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
        transaction.setStatus(TransactionStatus.valueOf(rs.getString("status")));
        transaction.setAdminId(rs.getLong("admin_id"));
        transaction.setDeleted(rs.getBoolean("deleted"));
        return transaction;
    };

    public Optional<Transaction> findById(Long id) {
        String sql = "SELECT * FROM transactions WHERE id = ?";
        List<Transaction> transactions = jdbcTemplate.query(sql, transactionRowMapper, id);
        return transactions.stream().findFirst();
    }

    public List<Transaction> findByStatus(TransactionStatus status) {
        String sql = "SELECT * FROM transactions WHERE status = ?";
        return jdbcTemplate.query(sql, transactionRowMapper, status.name());
    }

    public List<Transaction> findByAccountId(Long accountId) {
        String sql = "SELECT * FROM transactions WHERE from_account_id = ? OR to_account_id = ?";
        return jdbcTemplate.query(sql, transactionRowMapper, accountId, accountId);
    }

    public void save(Transaction transaction) {
        String sql = "INSERT INTO transactions (from_account_id, to_account_id, amount, currency, timestamp, status, admin_id, deleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                transaction.getFromAccountId(),
                transaction.getToAccountId(),
                transaction.getAmount(),
                transaction.getCurrency(),
                transaction.getTimestamp(),
                transaction.getStatus().name(),
                transaction.getAdminId(),
                transaction.isDeleted()
        );
    }

    public void update(Transaction transaction) {
        String sql = "UPDATE transactions SET status = ?, admin_id = ?, deleted = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                transaction.getStatus().name(),
                transaction.getAdminId(),
                transaction.isDeleted(),
                transaction.getId()
        );
    }
}
