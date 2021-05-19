package fr.poc.bankapp.domain.account;

import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * Operations related to record transactions.
 */
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long>{
	
}
