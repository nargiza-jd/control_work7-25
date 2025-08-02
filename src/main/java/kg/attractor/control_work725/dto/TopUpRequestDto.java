package kg.attractor.control_work725.dto;

import lombok.Data;

@Data
public class TopUpRequestDto {
    private Long accountId;
    private double amount;
}