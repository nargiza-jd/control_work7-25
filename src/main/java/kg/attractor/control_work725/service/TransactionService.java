package kg.attractor.control_work725.service;

import kg.attractor.control_work725.dto.TransactionCreateDto;
import kg.attractor.control_work725.model.Transaction;

import java.util.List;

public interface TransactionService {
    void createTransaction(TransactionCreateDto transactionDto);
    List<Transaction> getTransactionHistory(Long accountId);
    List<Transaction> getAllTransactions();
    List<Transaction> getPendingTransactions();
    void approveTransaction(Long transactionId);
    void rollbackTransaction(Long transactionId);
    void deleteTransaction(Long id);
}
