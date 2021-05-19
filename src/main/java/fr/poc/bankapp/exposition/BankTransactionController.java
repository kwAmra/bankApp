package fr.poc.bankapp.exposition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.poc.bankapp.domain.account.AccountTransactionService;
import fr.poc.bankapp.domain.account.vo.AccountVo;
import fr.poc.bankapp.domain.account.vo.AccountWithHistoryVo;
import fr.poc.bankapp.exposition.request.TransactionAccountRequest;

/**
 * Bank Rest API controller.<br>
 * It allowing operations :
 * <ul>
 * <li>Transfer : {@link #consultAccount(Long)}</li>
 * <li>Deposit : {@link #deposit(TransactionAccountRequest)}</li>
 * <li>Withdraw : {@link #withdraw(TransactionAccountRequest)}</li>
 * </ul>
 */
@RestController
public class BankTransactionController {

	/** Logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(BankTransactionController.class);

	/** {@link AccountTransactionService}. */
	@Autowired
	private AccountTransactionService bankTransactionService;
	
	
	/**
     * Recover an account information by account number.
     *
     * @param accountNumber
     * @return {@link AccountWithHistoryVo}.
     */
    @GetMapping(value = "/account/{accountNumber}")
    AccountWithHistoryVo consultAccount(@PathVariable Long accountNumber) {

        return bankTransactionService.consultAccountWithHistory(accountNumber);
    }

	/**
	 * Method allowing to deposit funds
	 * 
	 * @param tx {@link TransactionAccountRequest}
	 * @return {@link AccountVo}
	 */
    @PutMapping("/deposit")
	public AccountVo deposit(@RequestBody TransactionAccountRequest tx) {

		LOGGER.info("Start deposit transaction.");

		// Call deposit method.
		AccountVo account = bankTransactionService.deposit(tx.getOperatingAccount(),tx.getAmount());

		return account;
	}

	/**
	 * Method allowing to withdraw funds
	 * 
	 * @param tx {@link TransactionAccountRequest}
	 * @return {@link AccountVo}
	 */
	@PutMapping("/withdraw")
	public AccountVo withdraw(@RequestBody TransactionAccountRequest tx) {

		LOGGER.info("Start withdraw transaction.");

		// Call withdraw method.
		AccountVo account = bankTransactionService.withdraw(tx.getOperatingAccount(), tx.getAmount());

		return account;
	}



}
