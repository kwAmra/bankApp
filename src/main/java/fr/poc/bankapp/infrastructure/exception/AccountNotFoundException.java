package fr.poc.bankapp.infrastructure.exception;

/**
 * Business Account not found exception.
 */
public class AccountNotFoundException extends AbstractBusinessException {
	
	 /** UID. */
    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     */
    public AccountNotFoundException(Object... messageParams) {
        super(messageParams);
    }


}
