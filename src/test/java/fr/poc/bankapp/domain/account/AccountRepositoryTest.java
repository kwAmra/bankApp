package fr.poc.bankapp.domain.account;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * TU {@link AccountRepository}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(value = "fr.poc.bankapp.domain.account.AccountRepository")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class AccountRepositoryTest {

	/** {@link AccountRepository} */
	@Autowired
	private AccountRepository accountRepository;
	
	
	/**
	 * Tests {@link AccountRepository#findByAccountNumber(Long)}.
	 */
	@Sql(scripts="classpath:dataset/account_repository_dataset.sql" )
	@Test
	public void testFindByAccountNumber() {
		
		// Run method
		Account account  = accountRepository.findByAccountNumber(123L);
		
		// Assertion
		Assert.assertEquals(Long.valueOf(123), account.getAccountNumber());
		Assert.assertEquals(BigDecimal.valueOf(2500.12), account.getBalance());
		Assert.assertEquals(2, account.getTransactions().size());
		
	}
	
}
