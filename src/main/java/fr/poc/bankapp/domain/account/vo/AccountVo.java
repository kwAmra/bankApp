package fr.poc.bankapp.domain.account.vo;


import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Account value object informations.
 */
@SuperBuilder
@NoArgsConstructor
@Getter
public class AccountVo extends AbstractAccountVo{

	/** UID */
	private static final long serialVersionUID = 1L;

	public AccountVo(Long accountNumber, BigDecimal balance) {
		super(accountNumber, balance);
	}

}
