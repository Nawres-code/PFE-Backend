package rimenergyapi.exception.model;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	private String details;

	private HttpStatus status;

	private ExceptionType exceptionType;

	public CustomException(String message) {
		this.message = message;
		this.status = HttpStatus.NOT_FOUND;
		this.exceptionType = ExceptionType.DEFAULT_EXCEPTION;
	}
	
	public CustomException(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
		this.exceptionType = ExceptionType.DEFAULT_EXCEPTION;
	}

	public CustomException(String message, String details) {
		super();
		this.message = message;
		this.details = details;
		this.status = HttpStatus.NOT_FOUND;
		this.exceptionType = ExceptionType.DEFAULT_EXCEPTION;
	}
	
	public CustomException(String message, String details, HttpStatus status, ExceptionType exceptionType) {
		super();
		this.message = message;
		this.details = details;
		this.status = status;
		this.exceptionType = exceptionType;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return status;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public ExceptionType getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(ExceptionType exceptionType) {
		this.exceptionType = exceptionType;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
