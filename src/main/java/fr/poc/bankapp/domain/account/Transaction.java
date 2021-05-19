package fr.poc.bankapp.domain.account;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Transaction's informations.
 */
@Entity
@AllArgsConstructor()
@NoArgsConstructor
@Getter
@Table(name = "transaction")
public class Transaction {

	/** Transaction identifier. */
	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "transactionSeq", sequenceName = "TRANSACTION_TABLE_SEQ",allocationSize = 1)
	@GeneratedValue(generator = "transactionSeq")
	private Long id;

	/** Transaction type {@link TransactionType} . */
	@Column(name = "transaction_type")
	@Enumerated(EnumType.STRING)
	private TransactionType type;

	/** Transaction date. */
	@Column(name = "transaction_date")
	@Temporal(TemporalType.DATE)
	private Date date;

	/** Transaction amount. */
	@Column(name = "transaction_amount")
	private BigDecimal transactionAmount;
	
	/** Operating {@link Account} transaction. */
	@ManyToOne @JoinColumn(name="accountNumber", nullable=false)
    private Account account;
	
	/**
	 * Transaction constructor.
	 * @param type
	 * @param date
	 * @param transactionAmount
	 * @param account
	 */
	public Transaction(TransactionType type, Date date, BigDecimal transactionAmount, Account account) {
		this.type = type;
		this.date = date;
		this.transactionAmount = transactionAmount;
		this.account = account;
	}

}
