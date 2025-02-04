package rimenergyapi.exception.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import rimenergyapi.exception.model.ExceptionType;

public class ExceptionResponse {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;

	private String message;

	private String details;

	private String debugMessage;

	private List<String> subExceptions;

	private HttpStatus status;

	private ExceptionType exceptionType;

	private ExceptionResponse() {
		timestamp = LocalDateTime.now();
	}

	public ExceptionResponse(String message, String details) {
		this();
		this.message = message;
		this.details = details;
	}

	public ExceptionResponse(String message, String details, String debugMessage, HttpStatus status) {
		this();
		this.message = message;
		this.details = details;
		this.debugMessage = debugMessage;
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}

	public List<String> getSubExceptions() {
		return subExceptions;
	}

	public void setSubExceptions(List<String> subExceptions) {
		this.subExceptions = subExceptions;
	}

	public ExceptionType getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(ExceptionType exceptionType) {
		this.exceptionType = exceptionType;
	}

}