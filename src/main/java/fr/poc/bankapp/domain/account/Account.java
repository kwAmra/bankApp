package fr.poc.bankapp.domain.account;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Account's information.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "account")
public class Account {

	/** Account number, the attribute permits to identify the account. */
	@Id
	@SequenceGenerator(name = "accountNbSeq", sequenceName = "ACCOUNT_TABLE_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "accountNbSeq")
	@Column(name = "account_number", unique = true, nullable = false, updatable = false)
	private Long accountNumber;

	/** Balance of the account at time. */
	@Column(name = "balance", nullable= false)
	private BigDecimal balance;
	
	/** Set of transactions history */
	@OneToMany( targetEntity=Transaction.class, mappedBy="account", fetch = FetchType.EAGER)
    private Set<Transaction> transactions;
	
	/**
	 * Method allowing update account balance.
	 * @param newBalance New value of the balance.
	 */
	public void updateBalance(BigDecimal newBalance) {
		balance = newBalance;
	}

}
