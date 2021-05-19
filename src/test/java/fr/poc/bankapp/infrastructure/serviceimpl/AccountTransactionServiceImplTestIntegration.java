package fr.poc.bankapp.infrastructure.serviceimpl;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.poc.bankapp.domain.account.AccountTransactionService;
import fr.poc.bankapp.domain.account.vo.AccountVo;
import fr.poc.bankapp.domain.account.vo.AccountWithHistoryVo;
import fr.poc.bankapp.domain.account.vo.TransactionVo;
import fr.poc.bankapp.infrastructure.exception.AccountNotFoundException;
import fr.poc.bankapp.infrastructure.exception.InsufficientBalanceException;

/**
 * TU {@link AccountTransactionServiceImpl}.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(value = "fr.poc.bankapp.domain")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql(scripts="classpath:dataset/account_repository_dataset.sql" )
public class AccountTransactionServiceImplTestIntegration {


	/** {@link AccountTransactionServiceImpl} */
	@Autowired
	private AccountTransactionServiceImpl bankTransactionService;

	/** Handling method's exceptions. */
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/**
	 * Tests {@link AccountTransactionService#consultAccount(Long)}
	 */
	@Test
	public void testConsultAccount_OK() {

		// Run method to test.
		AccountWithHistoryVo account = bankTransactionService.consultAccountWithHistory(123L);

		// Assertions.
		Assert.assertNotNull(account);
		Assert.assertEquals(Long.valueOf(123), account.getAccountNumber());
		Assert.assertEquals(BigDecimal.valueOf(2500.12), account.getBalance());
		Assert.assertEquals(2, account.getTransactions().size());
	}

	/**
	 * Tests {@link AccountTransactionService#consultAccount(Long)}
	 */
	@Test
	public void testConsultAccount_KO() {

		// Expected exception informations assert
		expectedException.expect(AccountNotFoundException.class);


		// Run method to test.
		bankTransactionService.consultAccountWithHistory(1L);
	}

	/**
	 * Tests {@link AccountTransactionService#deposit(TransactionVo)}
	 * 
	 * 
	 */
	@Test
	public void testDeposit() {


		// Run method to test.
		AccountVo accountVo = bankTransactionService.deposit(123L, BigDecimal.TEN);

		// Assertion
		Assert.assertNotNull(accountVo);
		Assert.assertEquals(Long.valueOf(123), accountVo.getAccountNumber());
		Assert.assertEquals(BigDecimal.valueOf(2510.12), accountVo.getBalance());
	}

	/**
	 * Tests {@link AccountTransactionService#withdraw(TransactionVo)}
	 */
	@Test
	public void testWithdraw() {


		// Run method to test.
		AccountVo accountVo = bankTransactionService.withdraw(123L, BigDecimal.TEN);

		// Assertion
		Assert.assertNotNull(accountVo);
		Assert.assertEquals(Long.valueOf(123), accountVo.getAccountNumber());
		Assert.assertEquals(BigDecimal.valueOf(2490.12), accountVo.getBalance());
	}

	/**
	 * Tests {@link AccountTransactionService#withdraw(TransactionVo)} with
	 * insufficient balance
	 */
	@Test
	public void testWithdraw_KO() {

		// Expected exception informations assert
		expectedException.expect(InsufficientBalanceException.class);

		// Run method to test.
		bankTransactionService.withdraw(123L,BigDecimal.valueOf(3000));
	}


}
