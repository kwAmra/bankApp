package fr.poc.bankapp.infrastructure.exception;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Exposition API error data.
 */
@Builder
@AllArgsConstructor
@Getter
public class ApiError implements Serializable {

    /** Serialization UID. */
    private static final long serialVersionUID = 1L;

    /** Error log tracker ID. */
    protected UUID uuid;

    /** Error time. */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    protected LocalDateTime timestamp;

    /** Error message. */
    protected String message;

    /** Technical Error message. */
    @JsonIgnore
    protected String debugMessage;


    /**
     * Build an {@link ApiError} from an AccountBusinessException and its decoded message.
     *
     * @param ex {@link AbstractBusinessException}
     * @param message Exception decoded message
     * @return Assembled ApiErro from AccountBusinessException
     */
    public static ApiError build(AbstractBusinessException ex, String message) {
        return new ApiError(ex.getUuid(), ex.getTimestamp(), message, ex.getDebugMessage());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Error occurring :").append(message).append("\n");
        sb.append("# Massage : ").append(message).append("\n");
        sb.append("# Tracker_ID : ").append(uuid).append("\n");
        sb.append("# Date :").append(timestamp).append("\n");
        sb.append("# Cause : ").append(debugMessage).append("\n");
        return sb.toString();
    }
}

