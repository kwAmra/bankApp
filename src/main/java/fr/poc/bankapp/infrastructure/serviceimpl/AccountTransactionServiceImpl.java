package fr.poc.bankapp.infrastructure.serviceimpl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.poc.bankapp.domain.account.Account;
import fr.poc.bankapp.domain.account.AccountRepository;
import fr.poc.bankapp.domain.account.AccountTransactionService;
import fr.poc.bankapp.domain.account.Transaction;
import fr.poc.bankapp.domain.account.TransactionRepository;
import fr.poc.bankapp.domain.account.TransactionType;
import fr.poc.bankapp.domain.account.vo.AccountVo;
import fr.poc.bankapp.domain.account.vo.AccountWithHistoryVo;
import fr.poc.bankapp.infrastructure.assembler.AccountAssembler;
import fr.poc.bankapp.infrastructure.exception.AbstractBusinessException;
import fr.poc.bankapp.infrastructure.exception.AccountNotFoundException;
import fr.poc.bankapp.infrastructure.exception.InsufficientBalanceException;

/**
 * {@link AccountTransactionService} implementation.
 */
@Service
@Transactional
public class AccountTransactionServiceImpl implements AccountTransactionService {
	
	/** Logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountTransactionServiceImpl.class);

	/** {@link AccountRepository} */
	@Autowired
	private AccountRepository accountRepository;

	/** {@link TransactionRepository} */
	@Autowired
	private TransactionRepository transactionRepository;

	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AccountWithHistoryVo consultAccountWithHistory(Long accountNumber) {
		
		LOGGER.info("Start fetching account information.");
		
		// Get deposit account.
		Account account = getAccountByAccountNumber(accountNumber);
		
		return AccountAssembler.toAccountWithHistoryVo(account);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AccountVo deposit(Long operatingAccount, BigDecimal transactionAmount) throws AbstractBusinessException {

		// Get deposit account.
		Account account = getAccountByAccountNumber(operatingAccount);

		// add funds.
		account.updateBalance(account.getBalance().add(transactionAmount));

		// Save the transaction.
		Transaction tx = new Transaction(TransactionType.DEPOSIT, new Date(System.currentTimeMillis()), transactionAmount, account);
		transactionRepository.save(tx);

		return AccountAssembler.toAccountVo(account);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AccountVo withdraw(Long operatingAccount, BigDecimal transactionAmount) {

		// Get account.
		Account account = getAccountByAccountNumber(operatingAccount);

		// Check if the account has sufficient funds.
		if (account.getBalance().compareTo(transactionAmount) == -1) {
			LOGGER.error("ERROR# Insufficient balance");
			throw new InsufficientBalanceException();
		}

		// Subtract funds.
		account.updateBalance(account.getBalance().subtract(transactionAmount));

		// Save the transaction.
		Transaction tx = new Transaction(TransactionType.WITHDRAWAL, new Date(System.currentTimeMillis()), transactionAmount, account);
		transactionRepository.save(tx);
		
		return AccountAssembler.toAccountVo(account);
	}


	/**
	 * Get {@link Account} by account number
	 *
	 * @param accountNumber Account number.
	 * @return {@link Account}
	 * @throws AbstractBusinessException
	 */
	private Account getAccountByAccountNumber(Long accountNumber) throws AbstractBusinessException {
		// Get deposit account.
		Account account = Optional.ofNullable(accountRepository.findByAccountNumber(accountNumber))
                .orElseThrow(() ->new AccountNotFoundException(accountNumber));

		return account;
	}




}
