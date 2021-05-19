package fr.poc.bankapp.domain.account;

import java.math.BigDecimal;

import fr.poc.bankapp.domain.account.vo.AccountVo;
import fr.poc.bankapp.domain.account.vo.AccountWithHistoryVo;
import fr.poc.bankapp.infrastructure.exception.AccountNotFoundException;
import fr.poc.bankapp.infrastructure.exception.InsufficientBalanceException;

/**
 * Service allowing to handle bank transactions.
 */
public interface AccountTransactionService {

	/**
	 * Method allowing to consult an account information and history.
	 * 
	 * @param accountNumber Account number.
	 * @return {@link AccountVo}
	 * @throws AccountNotFoundException
	 */
	AccountWithHistoryVo consultAccountWithHistory(Long accountNumber) throws AccountNotFoundException;

	/**
	 * Method allowing to deposit funds.
	 * 
	 * @param operatingAccount account target.
	 * @param transactionAmount amount of deposit
	 * 
	 * @return {@link AccountVo}
	 * @throws AccountNotFoundException
	 */
	AccountVo deposit(Long operatingAccount, BigDecimal transactionAmount) throws AccountNotFoundException;

	/**
	 * Method allowing to withdraw funds.
	 * 
	 * @param operatingAccount account target.
	 * @param transactionAmount amount of withdraw.
	 * 
	 * @return {@link AccountVo}
	 * @throws AccountNotFoundException, InsufficientBalanceException
	 */
	AccountVo withdraw(Long operatingAccount, BigDecimal transactionAmount)
			throws AccountNotFoundException, InsufficientBalanceException;

}
