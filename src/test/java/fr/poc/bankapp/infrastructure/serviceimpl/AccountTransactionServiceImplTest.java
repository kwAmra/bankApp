package fr.poc.bankapp.infrastructure.serviceimpl;

import java.math.BigDecimal;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.poc.bankapp.domain.account.Account;
import fr.poc.bankapp.domain.account.AccountRepository;
import fr.poc.bankapp.domain.account.AccountTransactionService;
import fr.poc.bankapp.domain.account.TransactionRepository;
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
public class AccountTransactionServiceImplTest {

	/** {@link AccountRepository} */
	@Mock
	private AccountRepository accountRepository;

	/** {@link TransactionRepository} */
	@Mock
	private TransactionRepository transactionRepository;

	/** {@link AccountTransactionServiceImpl} */
	@InjectMocks
	private AccountTransactionServiceImpl bankTransactionService;

	/** Handling method's exceptions. */
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/**
	 * Tests {@link AccountTransactionService#consultAccount(Long)}
	 */
	@Test
	public void testConsultAccount_OK() {

		// Init Mock.
		Mockito.when(accountRepository.findByAccountNumber(1L)).thenReturn(new Account(1L, BigDecimal.TEN, new HashSet<>()));

		// Run method to test.
		AccountWithHistoryVo account = bankTransactionService.consultAccountWithHistory(1L);

		// Assertions.
		Assert.assertNotNull(account);
		Assert.assertEquals(Long.valueOf(1), account.getAccountNumber());
		Assert.assertEquals(BigDecimal.TEN, account.getBalance());
		Assert.assertEquals(0, account.getTransactions().size());
	}

	/**
	 * Tests {@link AccountTransactionService#consultAccount(Long)}
	 */
	@Test
	public void testConsultAccount_KO() {

		// Expected exception informations assert
		expectedException.expect(AccountNotFoundException.class);

		// Init Mock.
		Mockito.when(accountRepository.findByAccountNumber(1L)).thenReturn(null);

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

		// Init Mock.
		Mockito.when(accountRepository.findByAccountNumber(1L)).thenReturn(new Account(1L, BigDecimal.ZERO, new HashSet<>()));

		// Run method to test.
		AccountVo accountVo = bankTransactionService.deposit(1L, BigDecimal.TEN);

		// Assertion
		Assert.assertNotNull(accountVo);
		Assert.assertEquals(Long.valueOf(1), accountVo.getAccountNumber());
		Assert.assertEquals(BigDecimal.TEN, accountVo.getBalance());
	}

	/**
	 * Tests {@link AccountTransactionService#withdraw(TransactionVo)}
	 */
	@Test
	public void testWithdraw() {

		// Init Mock.
		Mockito.when(accountRepository.findByAccountNumber(1L)).thenReturn(new Account(1L, BigDecimal.TEN, new HashSet<>()));

		// Run method to test.
		AccountVo accountVo = bankTransactionService.withdraw(1L, BigDecimal.ONE);

		// Assertion
		Assert.assertNotNull(accountVo);
		Assert.assertEquals(Long.valueOf(1), accountVo.getAccountNumber());
		Assert.assertEquals(BigDecimal.valueOf(9), accountVo.getBalance());
	}

	/**
	 * Tests {@link AccountTransactionService#withdraw(TransactionVo)} with
	 * insufficient balance
	 */
	@Test
	public void testWithdraw_KO() {

		// Expected exception informations assert
		expectedException.expect(InsufficientBalanceException.class);

		// Init Mock.
		Mockito.when(accountRepository.findByAccountNumber(1L)).thenReturn(new Account(1L, BigDecimal.ONE, new HashSet<>()));

		// Run method to test.
		bankTransactionService.withdraw(1L,BigDecimal.TEN);
	}


}
