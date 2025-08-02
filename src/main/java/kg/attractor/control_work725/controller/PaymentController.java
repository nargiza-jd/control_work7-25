package kg.attractor.control_work725.controller;

import jakarta.validation.Valid;
import kg.attractor.control_work725.dto.AccountCreateDto;
import kg.attractor.control_work725.dto.TransactionCreateDto;
import kg.attractor.control_work725.model.User;
import kg.attractor.control_work725.service.AccountService;
import kg.attractor.control_work725.service.TransactionService;
import kg.attractor.control_work725.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentController {

    private final UserService userService;
    private final AccountService accountService;
    private final TransactionService transactionService;

    @PostMapping("/accounts")
    public ResponseEntity<String> createAccount(@Valid @RequestBody AccountCreateDto accountDto, @AuthenticationPrincipal User user) {
        accountService.createAccount(accountDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Счет создан успешно!");
    }

    @GetMapping("/accounts/balance")
    public ResponseEntity<Double> getAccountBalance(@AuthenticationPrincipal User user) {
        // TODO: Реализовать логику получения баланса
        return ResponseEntity.ok(0.0);
    }

    @PostMapping("/accounts/balance")
    public ResponseEntity<String> topUpAccount(@RequestBody Double amount, @AuthenticationPrincipal User user) {
        // TODO: Реализовать логику пополнения счета
        return ResponseEntity.ok("Счет успешно пополнен!");
    }

    @GetMapping("/accounts")
    public ResponseEntity<?> getAccounts(@AuthenticationPrincipal User user) {
        // TODO: Реализовать логику получения списка счетов для текущего пользователя
        return ResponseEntity.ok("Список счетов");
    }

    @PostMapping("/transactions")
    public ResponseEntity<String> createTransaction(@Valid @RequestBody TransactionCreateDto transactionDto, @AuthenticationPrincipal User user) {
        transactionService.createTransaction(transactionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Транзакция создана успешно!");
    }

    @GetMapping("/transactions/{accountId}/history")
    public ResponseEntity<?> getTransactionHistory(@PathVariable Long accountId) {
        return ResponseEntity.ok(transactionService.getTransactionHistory(accountId));
    }
}
