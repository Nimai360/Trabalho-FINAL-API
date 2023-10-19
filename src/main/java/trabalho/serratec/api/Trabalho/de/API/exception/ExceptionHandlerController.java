package trabalho.serratec.api.Trabalho.de.API.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {//extends ResponseEntityExceptionHandler  {
	
//	@ExceptionHandler({EmailException.class, SenhaException.class})
//	protected ResponseEntity<?> handleEmailException(Exception ex) {
//		return ResponseEntity.unprocessableEntity().body(ex.getMessage());
//	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMostSpecificCause().getMessage());
	}

}
