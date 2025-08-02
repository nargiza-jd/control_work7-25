package kg.attractor.control_work725.service.impl;

import kg.attractor.control_work725.dao.AccountDao;
import kg.attractor.control_work725.dao.TransactionDao;
import kg.attractor.control_work725.dto.TransactionCreateDto;
import kg.attractor.control_work725.model.Transaction;
import kg.attractor.control_work725.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionDao transactionDao;
    private final AccountDao accountDao;

    @Override
    public void createTransaction(TransactionCreateDto transactionDto) {
        // TODO: Добавить логику проверки наличия средств перед созданием
        Transaction transaction = new Transaction();
        transaction.setFromAccountId(transactionDto.getFromAccountId());
        transaction.setToAccountId(transactionDto.getToAccountId());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setCurrency(transactionDto.getCurrency());
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setStatus("PENDING");

        transactionDao.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionHistory(Long accountId) {
        // TODO: Реализовать логику получения истории транзакций для конкретного счета
        return new ArrayList<>();
    }

    @Override
    public List<Transaction> getAllTransactions() {
        // TODO: Реализовать логику получения всех транзакций из базы данных
        return new ArrayList<>();
    }

    @Override
    public List<Transaction> getPendingTransactions() {
        // TODO: Реализовать логику получения транзакций со статусом "PENDING"
        return transactionDao.findByStatus("PENDING");
    }

    @Override
    public void approveTransaction(Long transactionId) {
        // TODO: Реализовать логику одобрения транзакции
    }

    @Override
    public void rollbackTransaction(Long transactionId) {
        // TODO: Реализовать логику отката транзакции
    }

    @Override
    public void deleteTransaction(Long id) {
        // TODO: Реализовать логику "удаления" транзакции (лучше не удалять, а менять статус)
    }
}
