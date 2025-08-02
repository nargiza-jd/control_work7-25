package kg.attractor.control_work725.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Long id;
    private Long userId;
    private String currency;
    private double balance;
}
