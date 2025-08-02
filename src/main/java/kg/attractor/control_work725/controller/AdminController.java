 package kg.attractor.control_work725.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import kg.attractor.control_work725.service.TransactionService;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final TransactionService transactionService;
    @GetMapping("/transactions")
    public ResponseEntity<?> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/transactions/approval")
    public ResponseEntity<?> getPendingTransactions() {
        return ResponseEntity.ok(transactionService.getPendingTransactions());
    }

    @PostMapping("/transactions/approval")
    public ResponseEntity<String> approveTransaction(@RequestBody Long transactionId) {
        transactionService.approveTransaction(transactionId);
        return ResponseEntity.ok("Транзакция успешно одобрена!");
    }

    @PostMapping("/transactions/rollback")
    public ResponseEntity<String> rollbackTransaction(@RequestBody Long transactionId) {
        transactionService.rollbackTransaction(transactionId);
        return ResponseEntity.ok("Транзакция успешно откатана!");
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok("Транзакция успешно удалена!");
    }
}

