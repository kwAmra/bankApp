package fr.poc.bankapp.infrastructure.exception;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;

/**
 * Common business exceptions.
 */
@Getter
public abstract class AbstractBusinessException extends RuntimeException {

    /** UID. */
    private static final long serialVersionUID = 1L;

    /** Error log tracker UUID. */
    protected final UUID uuid = UUID.randomUUID();

    /** Error message. */
    private String message;
    
    /** Error time. */
    protected final LocalDateTime timestamp = LocalDateTime.now();
    
    /** Technical debug message. */
    protected String debugMessage;

    /** Specific parameters for building error message. */
    protected Object[] paramsMessage;


    /**
     * Constructor whit message and the original exception.
     *
     * @param message Business exception message.
     * @param ex Original exception data.
     * @param msgParams Business message parameters.
     */
    protected AbstractBusinessException(String message, Throwable ex, Object... msgParams) {
        this.message = message;
        debugMessage = ex.getLocalizedMessage();
        paramsMessage = msgParams;
    }
    
    /**
     * Constructor whit message parameter for controller handler.
     *
     * @param msgParams Message parameters
     */
    protected AbstractBusinessException(Object... msgParams) {
    	paramsMessage = msgParams;
    }

    
    
}