package fr.poc.bankapp.infrastructure.assembler;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

import fr.poc.bankapp.domain.account.Account;
import fr.poc.bankapp.domain.account.Transaction;
import fr.poc.bankapp.domain.account.TransactionType;
import fr.poc.bankapp.domain.account.vo.AccountVo;
import fr.poc.bankapp.domain.account.vo.AccountWithHistoryVo;

/**
 * Unit test for {@link AccountAssembler}
 */
public class AccountAssemblerTest {

	
	/**
	 * TU {@link AccountAssembler#toAccountVo(Account)}.
	 */
	@Test
	public void testToAccountVo() {
		// Init test data
		Account account = new Account(123L, BigDecimal.TEN, new HashSet<>());
		Transaction tx = new Transaction(1L, TransactionType.DEPOSIT, new Date(), BigDecimal.TEN, account);
		account.getTransactions().add(tx);
		// Run method to test.
		AccountVo accountVo = AccountAssembler.toAccountVo(account);

		// Assertion.
		Assert.assertNotNull(accountVo);
		Assert.assertEquals(Long.valueOf(123), accountVo.getAccountNumber());
		Assert.assertEquals(BigDecimal.valueOf(10), accountVo.getBalance());
	}
	
	/**
	 * TU {@link AccountAssembler#toAccountWithHistoryVo(Account)}.
	 */
	@Test
	public void testToAccountWithHistoryVo() {
		// Init test data
		Account account = new Account(123L, BigDecimal.TEN, new HashSet<>());
		Transaction tx = new Transaction(1L, TransactionType.DEPOSIT, new Date(), BigDecimal.TEN, account);
		account.getTransactions().add(tx);
		// Run method to test.
		AccountWithHistoryVo accountHistoryVo = AccountAssembler.toAccountWithHistoryVo(account);

		// Assertion.
		Assert.assertNotNull(accountHistoryVo);
		Assert.assertEquals(Long.valueOf(123), accountHistoryVo.getAccountNumber());
		Assert.assertEquals(BigDecimal.valueOf(10), accountHistoryVo.getBalance());
		Assert.assertEquals(1, accountHistoryVo.getTransactions().size());
	}
}
