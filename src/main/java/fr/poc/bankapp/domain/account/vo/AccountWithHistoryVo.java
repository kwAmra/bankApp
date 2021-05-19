package fr.poc.bankapp.domain.account.vo;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Account value object informations.
 */
@SuperBuilder
@Getter
public class AccountWithHistoryVo extends AbstractAccountVo {

	/** UID */
	private static final long serialVersionUID = 1L;

	/**
	 * All args constructor.
	 * 
	 * @param accountNumber
	 * @param balance
	 * @param txs
	 */
	public AccountWithHistoryVo(Long accountNumber, BigDecimal balance, Set<TransactionVo> txs) {

		super(accountNumber, balance);
		// Use a copy of the returned set.
		transactions = new HashSet<>(txs);
	}

	/** History of account transactions. */
	private Set<TransactionVo> transactions;

}
