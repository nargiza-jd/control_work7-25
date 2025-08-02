package kg.attractor.control_work725.controller;

import jakarta.validation.Valid;
import kg.attractor.control_work725.dto.AccountCreateDto;
import kg.attractor.control_work725.dto.TransactionCreateDto;
import kg.attractor.control_work725.dto.UserRegisterDto;
import kg.attractor.control_work725.service.AccountService;
import kg.attractor.control_work725.service.TransactionService;
import kg.attractor.control_work725.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentController {

    private final UserService userService;
    private final AccountService accountService;
    private final TransactionService transactionService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegisterDto userDto) {
        userService.registerUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Пользователь зарегистрирован успешно!");
    }

    @PostMapping("/accounts")
    public ResponseEntity<String> createAccount(@Valid @RequestBody AccountCreateDto accountDto) {
        accountService.createAccount(accountDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Счет создан успешно!");
    }

    @GetMapping("/accounts/balance")
    public ResponseEntity<Double> getAccountBalance() {
        double balance = accountService.getAccountBalance();
        return ResponseEntity.ok(balance);
    }

    @PostMapping("/accounts/balance")
    public ResponseEntity<String> topUpAccount(@RequestBody Double amount) {
        accountService.topUpAccount(amount);
        return ResponseEntity.ok("Счет успешно пополнен!");
    }

    @GetMapping("/accounts")
    public ResponseEntity<?> getAccounts() {
        return ResponseEntity.ok(accountService.getAccounts());
    }

    @PostMapping("/transactions")
    public ResponseEntity<String> createTransaction(@Valid @RequestBody TransactionCreateDto transactionDto) {
        transactionService.createTransaction(transactionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Транзакция создана успешно!");
    }

    @GetMapping("/transactions/{accountId}/history")
    public ResponseEntity<?> getTransactionHistory(@PathVariable Long accountId) {
        return ResponseEntity.ok(transactionService.getTransactionHistory(accountId));
    }
}
