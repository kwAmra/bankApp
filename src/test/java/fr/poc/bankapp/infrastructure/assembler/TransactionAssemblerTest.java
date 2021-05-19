package fr.poc.bankapp.infrastructure.assembler;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

import fr.poc.bankapp.domain.account.Account;
import fr.poc.bankapp.domain.account.Transaction;
import fr.poc.bankapp.domain.account.TransactionType;
import fr.poc.bankapp.domain.account.vo.TransactionVo;
import fr.poc.bankapp.exposition.request.TransactionAccountRequest;

/**
 * Unit test for {@link TransactionAssembler}
 */
public class TransactionAssemblerTest {

	/**
	 * TU {@link TransactionAssembler#toTransactionVo(TransactionAccountRequest)}.
	 */
	@Test
	public void testToTransactionVo() {
		// Init test data
		Account account = new Account(123L, BigDecimal.ZERO, new HashSet<>());
		Transaction tx = new Transaction(1L, TransactionType.DEPOSIT, new Date(), BigDecimal.TEN, account);

		// Run method to test.
		TransactionVo txVo = TransactionAssembler.toTransactionVo(tx);

		// Assertion.
		Assert.assertNotNull(txVo);
		Assert.assertEquals(Long.valueOf(123), txVo.getOperatingAccount());
		Assert.assertEquals(BigDecimal.valueOf(10), txVo.getTransactionAmount());
		Assert.assertEquals(TransactionType.DEPOSIT, txVo.getTransactionType());
	}

}
