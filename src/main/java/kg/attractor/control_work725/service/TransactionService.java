package kg.attractor.control_work725.service;

import kg.attractor.control_work725.dto.TransactionCreateDto;

public interface TransactionService {
    void createTransaction(TransactionCreateDto transactionDto);
}
