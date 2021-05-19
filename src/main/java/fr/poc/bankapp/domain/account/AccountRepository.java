package fr.poc.bankapp.domain.account;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Operations related to accounts persistence.
 */
public interface AccountRepository extends PagingAndSortingRepository<Account, Long>{
	
	/**
	 * Gets {@link Account} by it's account number.
	 * @param accountNumber Account number.
	 * @return {@link Account}.
	 */
	public Account findByAccountNumber(Long accountNumber);

}
