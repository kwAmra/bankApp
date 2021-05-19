package fr.poc.bankapp.domain.account.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Base account value object informations.
 */
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
@Getter
public abstract class AbstractAccountVo implements Serializable{
	
	/** UID	 */
	private static final long serialVersionUID = 1L;

	/** Account Number. */
	protected Long accountNumber;

	/** Balance of the account at time. */
	protected BigDecimal balance;

}
