package fr.poc.bankapp.domain.account.vo;

import java.math.BigDecimal;

import fr.poc.bankapp.domain.account.Account;
import fr.poc.bankapp.domain.account.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Transaction value object informations.
 */
@AllArgsConstructor
@Builder
@Getter
public class TransactionVo {

	/** Operating {@link Account} transaction. */
	private Long operatingAccount;

	/** Transaction amount. */
	private BigDecimal transactionAmount;
	
	/** Transaction amount. */
	private TransactionType transactionType;
	
	/** Formated transaction date. */
	private String transactionDate;

}
