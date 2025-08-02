package kg.attractor.control_work725.service.impl;

import kg.attractor.control_work725.dao.AccountDao;
import kg.attractor.control_work725.dao.TransactionDao;
import kg.attractor.control_work725.dto.TransactionCreateDto;
import kg.attractor.control_work725.exceptions.NotFoundException;
import kg.attractor.control_work725.model.Account;
import kg.attractor.control_work725.model.Transaction;
import kg.attractor.control_work725.model.TransactionStatus;
import kg.attractor.control_work725.model.User;
import kg.attractor.control_work725.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionDao transactionDao;
    private final AccountDao accountDao;

    @Override
    public void createTransaction(TransactionCreateDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setFromAccountId(transactionDto.getFromAccountId());
        transaction.setToAccountId(transactionDto.getToAccountId());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setCurrency(transactionDto.getCurrency());
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setStatus(TransactionStatus.PENDING);
        transaction.setDeleted(false);
        transactionDao.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionHistory(Long accountId) {
        return transactionDao.findByAccountId(accountId);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionDao.findByStatus(TransactionStatus.APPROVED);
    }

    @Override
    public List<Transaction> getPendingTransactions() {
        return transactionDao.findByStatus(TransactionStatus.PENDING);
    }

    @Override
    public void approveTransaction(Long transactionId) {
        log.info("Админ подтверждает транзакцию {}", transactionId);
        Transaction tx = transactionDao.findById(transactionId).orElseThrow(() -> new NotFoundException("Transaction"));
        if (!tx.getStatus().equals(TransactionStatus.PENDING)) {
            throw new IllegalStateException("Транзакция уже обработана");
        }
        Account from = accountDao.findById(tx.getFromAccountId()).orElseThrow(() -> new NotFoundException("From Account"));
        Account to = accountDao.findById(tx.getToAccountId()).orElseThrow(() -> new NotFoundException("To Account"));
        if (from.getBalance() < tx.getAmount()) {
            throw new IllegalStateException("Недостаточно средств");
        }
        from.setBalance(from.getBalance() - tx.getAmount());
        to.setBalance(to.getBalance() + tx.getAmount());
        accountDao.update(from);
        accountDao.update(to);

        tx.setStatus(TransactionStatus.APPROVED);
        tx.setAdminId(getCurrentAdminId());
        transactionDao.update(tx);
    }

    @Override
    public void rollbackTransaction(Long transactionId) {
        log.info("Админ откатывает транзакцию {}", transactionId);
        Transaction tx = transactionDao.findById(transactionId).orElseThrow(() -> new NotFoundException("Transaction"));

        if (!tx.getStatus().equals(TransactionStatus.APPROVED)) {
            throw new IllegalStateException("Можно откатить только одобренную транзакцию");
        }

        Account to = accountDao.findById(tx.getToAccountId()).orElseThrow(() -> new NotFoundException("Получатель"));
        Account from = accountDao.findById(tx.getFromAccountId()).orElseThrow(() -> new NotFoundException("Отправитель"));

        if (to.getBalance() < tx.getAmount()) {
            throw new IllegalStateException("Недостаточно средств для отката");
        }

        to.setBalance(to.getBalance() - tx.getAmount());
        from.setBalance(from.getBalance() + tx.getAmount());

        accountDao.update(to);
        accountDao.update(from);

        tx.setStatus(TransactionStatus.ROLLBACKED);
        tx.setAdminId(getCurrentAdminId());
        transactionDao.update(tx);
    }

    @Override
    public void deleteTransaction(Long id) {
        log.info("Админ удаляет транзакцию {}", id);
        Transaction tx = transactionDao.findById(id).orElseThrow(() -> new NotFoundException("Transaction"));

        if (!tx.getStatus().equals(TransactionStatus.ROLLBACKED)) {
            throw new IllegalStateException("Удалить можно только откатанные транзакции");
        }

        tx.setStatus(TransactionStatus.DELETED);
        transactionDao.update(tx);
    }

    private Long getCurrentAdminId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof User user) {
            return user.getId();
        }
        throw new IllegalStateException("Админ не найден в контексте");
    }
}