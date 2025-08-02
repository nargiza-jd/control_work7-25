package kg.attractor.control_work725.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RollbackLog {
    private Long id;
    private Long transactionId;
    private String reason;
    private LocalDateTime date;
}
