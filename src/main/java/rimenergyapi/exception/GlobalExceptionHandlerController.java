package rimenergyapi.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mail.MailException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import rimenergyapi.exception.dto.ExceptionResponse;
import rimenergyapi.exception.model.CustomException;
import rimenergyapi.exception.model.ExceptionType;
import rimenergyapi.security.exception.ErrorMessage;
import rimenergyapi.security.exception.TokenRefreshException;

@ControllerAdvice
@RestController
public class GlobalExceptionHandlerController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Object> handleCustomException(HttpServletResponse res, CustomException ex) {
		// map custom exception to exception response !
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), ex.getDetails(), null,
				ex.getHttpStatus());
		// exception index !
		exceptionResponse.setExceptionType(ex.getExceptionType());
		return new ResponseEntity<>(exceptionResponse, ex.getHttpStatus());
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> handleAccessDeniedException(HttpServletResponse res, AccessDeniedException ex) {
		// map custom exception to exception response !
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				"Hi, You dont have the permission to access this ressource, thank you !", ex.getMessage(), null,
				HttpStatus.INTERNAL_SERVER_ERROR);
		// exception index !
		exceptionResponse.setExceptionType(ExceptionType.INTERNAL_EXCEPTION);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	  
	@ExceptionHandler(value = TokenRefreshException.class)
	  //@ResponseStatus(HttpStatus.FORBIDDEN)
	  public ErrorMessage handleTokenRefreshException(TokenRefreshException ex, WebRequest request) {
	    return new ErrorMessage(
	        HttpStatus.FORBIDDEN.value(),
	        new Date(),
	        ex.getMessage(),
	        request.getDescription(false));
	  }

	@ExceptionHandler(MailException.class)
	public ResponseEntity<Object> handleMailException(HttpServletResponse res, MailException ex) {
		// map custom exception to exception response !
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				"Mail Server Error - Please contact your administrator, Thank you !", ex.getMessage(), null,
				HttpStatus.INTERNAL_SERVER_ERROR);
		// exception index !
		exceptionResponse.setExceptionType(ExceptionType.INTERNAL_EXCEPTION);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				"Server Error - Please contact your administrator, Thank you !", request.getDescription(false),
				sw.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		// exception index !
		exceptionResponse.setExceptionType(ExceptionType.INTERNAL_EXCEPTION);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse("Validation Failed !", null,
				ex.getBindingResult().toString(), HttpStatus.NOT_FOUND);
		List<String> reuslt = ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.collect(Collectors.toList());
		// map sub exception !
		exceptionResponse.setSubExceptions(reuslt);
		// exception index !
		exceptionResponse.setExceptionType(ExceptionType.VALIDATION_EXCEPTION);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
}