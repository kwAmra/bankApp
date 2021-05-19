package fr.poc.bankapp.exposition.request;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import fr.poc.bankapp.domain.account.Account;
import lombok.Data;

@Data
public class TransactionAccountRequest implements Serializable{

	/** Unique serialization ID. */
	private static final long serialVersionUID = -2566576274091129700L;

	/** Operating {@link Account} transaction. */
	@NotNull
	private Long operatingAccount;

	/** Transaction amount. */
	@NotNull
	private BigDecimal amount;


}
