package kg.attractor.control_work725.dao;

import kg.attractor.control_work725.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role"));
        user.setBlocked(rs.getBoolean("is_blocked"));
        return user;
    };

    public Optional<User> findByPhoneNumber(String phoneNumber) {
        String sql = "SELECT * FROM users WHERE phone_number = ?";
        List<User> users = jdbcTemplate.query(sql, userRowMapper, phoneNumber);
        return users.stream().findFirst();
    }

    public void save(User user) {
        String sql = "INSERT INTO users (phone_number, name, password, role, is_blocked) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getPhoneNumber(), user.getName(), user.getPassword(), user.getRole(), user.isBlocked());
    }
}
