package fr.poc.bankapp.infrastructure.assembler;

import java.util.Set;
import java.util.stream.Collectors;

import fr.poc.bankapp.domain.account.Account;
import fr.poc.bankapp.domain.account.vo.AccountVo;
import fr.poc.bankapp.domain.account.vo.AccountWithHistoryVo;
import fr.poc.bankapp.domain.account.vo.TransactionVo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Assembler allowing to transform {@link Account} to domain object.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AccountAssembler {

	
	/**
	 * Assemble a {@link AccountVo} from {@link Account}.
	 * 
	 * @param account {@link Account}
	 * @return {@link AccountVo}
	 */
	public static AccountVo toAccountVo(Account account) {

		// Build an account value object from domain account
		return AccountVo.builder().accountNumber(account.getAccountNumber()).balance(account.getBalance()).build();
	}
	
	/**
	 * Assemble a {@link AccountWithHistoryVo} from {@link Account}.
	 * 
	 * @param request {@link Account}
	 * @return {@link AccountWithHistoryVo}
	 */
	public static AccountWithHistoryVo toAccountWithHistoryVo(Account account) {

		// Transform transactions collection to transactions value objects collection.
		Set<TransactionVo> txsVo = account.getTransactions().stream().map(tx -> TransactionAssembler.toTransactionVo(tx)).collect(Collectors.toSet());
		
		return AccountWithHistoryVo.builder().accountNumber(account.getAccountNumber()).balance(account.getBalance()).transactions(txsVo).build();
	}



}
