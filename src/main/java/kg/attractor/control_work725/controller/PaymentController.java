package kg.attractor.control_work725.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public class PaymentController {
    // private final UserService userService;
    // private final AccountService accountService;
    // private final TransactionService transactionService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterDto userDto) {
        return ResponseEntity.ok("Пользователь зарегистрирован успешно!");
    }

    @PostMapping("/accounts")
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountCreateDto accountDto) {
        return ResponseEntity.ok("Счет создан успешно!");
    }

    @GetMapping("/accounts/balance")
    public ResponseEntity<?> getAccountBalance() {
        return ResponseEntity.ok("Баланс счета: 100.00 KGS");
    }

    @PostMapping("/transactions")
    public ResponseEntity<?> createTransaction(@Valid @RequestBody TransactionCreateDto transactionDto) {
        return ResponseEntity.ok("Транзакция создана успешно!");
    }

    @GetMapping("/transactions/{accountId}/history")
    public ResponseEntity<?> getTransactionHistory(@PathVariable Long accountId) {
        return ResponseEntity.ok("История транзакций для счета " + accountId);
    }
}
