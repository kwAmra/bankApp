package fr.poc.bankapp.infrastructure.assembler;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

import fr.poc.bankapp.domain.account.Transaction;
import fr.poc.bankapp.domain.account.vo.TransactionVo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Assembler allowing to handle transformation of transaction object
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TransactionAssembler {
	
	public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  

	/**
	 * Assemble a {@link TransactionVo} from a {@link Transaction}
	 * @param tx {@link Transaction}
	 * @return {@link TransactionVo}
	 */
	public static TransactionVo toTransactionVo(Transaction tx) {

		return TransactionVo.builder().transactionAmount(tx.getTransactionAmount()).transactionDate(dateFormat.format(tx.getDate()))
				.transactionType(tx.getType()).operatingAccount(tx.getAccount().getAccountNumber()).build();
	}

}
