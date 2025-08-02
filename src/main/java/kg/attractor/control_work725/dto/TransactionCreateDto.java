package kg.attractor.control_work725.dto;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class TransactionCreateDto {

    @NotNull(message = "ID отправителя не может быть пустым.")
    @Min(value = 1, message = "ID отправителя должен быть больше 0.")
    private Long fromAccountId;

    @NotNull(message = "ID получателя не может быть пустым.")
    @Min(value = 1, message = "ID получателя должен быть больше 0.")
    private Long toAccountId;

    @NotNull(message = "Сумма не может быть пустой.")
    @Min(value = 1, message = "Сумма должна быть больше 0.")
    private Double amount;

    @NotNull(message = "Валюта не может быть пустой.")
    @Size(min = 3, max = 3, message = "Валюта должна состоять из 3 символов (например, 'USD', 'KGS').")
    private String currency;
}
