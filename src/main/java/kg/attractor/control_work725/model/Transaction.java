package kg.attractor.control_work725.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private Long id;
    private Long fromAccountId;
    private Long toAccountId;
    private double amount;
    private String currency;
    private LocalDateTime timestamp;
    private String status;
    private Long adminId;
}
