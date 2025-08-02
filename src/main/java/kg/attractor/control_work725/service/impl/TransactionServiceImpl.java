package kg.attractor.control_work725.service.impl;

import kg.attractor.control_work725.dao.AccountDao;
import kg.attractor.control_work725.dao.TransactionDao;
import kg.attractor.control_work725.model.Transaction;
import kg.attractor.control_work725.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionDao transactionDao;
    private final AccountDao accountDao;

    @Override
    public void createTransaction(TransactionCreateDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setStatus("APPROVED");

        transactionDao.save(transaction);
    }
}
