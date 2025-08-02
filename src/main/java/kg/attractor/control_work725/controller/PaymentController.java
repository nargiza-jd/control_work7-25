package kg.attractor.control_work725.controller;

import jakarta.validation.Valid;
import kg.attractor.control_work725.dto.AccountCreateDto;
import kg.attractor.control_work725.dto.TopUpRequestDto;
import kg.attractor.control_work725.dto.TransactionCreateDto;
import kg.attractor.control_work725.model.User;
import kg.attractor.control_work725.model.Account;
import kg.attractor.control_work725.service.AccountService;
import kg.attractor.control_work725.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    @PostMapping("/accounts")
    public ResponseEntity<String> createAccount(@Valid @RequestBody AccountCreateDto accountDto,
                                                @AuthenticationPrincipal User user) {
        accountService.createAccount(accountDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Счет создан успешно!");
    }

    @GetMapping("/accounts/balance")
    public ResponseEntity<Double> getAccountBalance(@RequestParam Long accountId,
                                                    @AuthenticationPrincipal User user) {
        double balance = accountService.getBalance(accountId, user);
        return ResponseEntity.ok(balance);
    }

    @PostMapping("/accounts/balance")
    public ResponseEntity<String> topUpAccount(@Valid @RequestBody TopUpRequestDto topUpRequest,
                                               @AuthenticationPrincipal User user) {
        accountService.topUpAccount(topUpRequest.getAccountId(), topUpRequest.getAmount(), user);
        return ResponseEntity.ok("Счет успешно пополнен!");
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAccounts(@AuthenticationPrincipal User user) {
        List<Account> accounts = accountService.getAccounts(user);
        return ResponseEntity.ok(accounts);
    }

    @PostMapping("/transactions")
    public ResponseEntity<String> createTransaction(@Valid @RequestBody TransactionCreateDto transactionDto,
                                                    @AuthenticationPrincipal User user) {
        transactionService.createTransaction(transactionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Транзакция создана успешно!");
    }

    @GetMapping("/transactions/{accountId}/history")
    public ResponseEntity<?> getTransactionHistory(@PathVariable Long accountId) {
        return ResponseEntity.ok(transactionService.getTransactionHistory(accountId));
    }
}