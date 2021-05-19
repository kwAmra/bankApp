package fr.poc.bankapp.infrastructure.exception;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controller exception handler.
 */
@RestControllerAdvice()
public class ControllerExceptionHandler {

    /** LOGGER */
    public static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /** Messages handler.*/
    @Autowired
    private MessageSource messageSource;

    /**
     * Handle {@link AccountNotFoundException}.
     *
     * @param exception {@link AccountNotFoundException}.
     * @return All exception information.
     */
    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ApiError handleAccountNotFoundException(AccountNotFoundException exception, Locale locale) {
        // Build an apiError
        ApiError apiError = ApiError.build(exception,
                messageSource.getMessage(exception.getClass().getSimpleName(), exception.getParamsMessage(), locale));

        LOGGER.error(apiError.toString());
        return apiError;
    }
    
    /**
     * Handle {@link InsufficientBalanceException}.
     *
     * @param exception {@link InsufficientBalanceException}.
     * @return All exception information.
     */
    @ExceptionHandler(InsufficientBalanceException.class)
    @ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
    public ApiError handleInsufficientBalanceException(InsufficientBalanceException exception, Locale locale) {
        // Build an apiError
        ApiError apiError = ApiError.build(exception,
                messageSource.getMessage(exception.getClass().getSimpleName(), exception.getParamsMessage(), locale));

        LOGGER.error(apiError.toString());
        return apiError;
    }
    
}
