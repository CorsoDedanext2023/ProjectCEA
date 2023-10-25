package it.dedagroup.project_cea.exception.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.dedagroup.project_cea.dto.response.ValidationErrorDTOResponse;
import it.dedagroup.project_cea.dto.response.ViolationDTOResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import it.dedagroup.project_cea.dto.response.MessageDtoResponse;

@RestControllerAdvice
public class ExceptionHandlerCustom {
	@ExceptionHandler(NotValidDataException.class)
	public ResponseEntity<MessageDtoResponse> handleInvalidData(NotValidDataException e){
		MessageDtoResponse m = new MessageDtoResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value(), e.getOggetto(),LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
	}
	//exceptionHandler per controllare i RequestBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MessageDtoResponse> validationError(MethodArgumentNotValidException e){
		Map<String,String> map = e.getBindingResult()
				.getFieldErrors().stream()
				.collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));
		List<String> errori = new ArrayList<>();
		for (String s : map.keySet()) {
			errori.add(s+": "+map.get(s));
		}
		MessageDtoResponse m = new MessageDtoResponse(errori, HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
	}


	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<MessageDtoResponse> userNotFound(UserNotFoundException e){
		MessageDtoResponse m = new MessageDtoResponse(e.getMessage(), HttpStatus.NOT_FOUND.value(), e.getRequest(),LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(m);
	}

	//ExceptionHandler per gestire le ConstraintViolations(PathVariable e RequestParam)
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	ValidationErrorDTOResponse onCostraintValidationException(ConstraintViolationException e){
		ValidationErrorDTOResponse error=new ValidationErrorDTOResponse();
		for (ConstraintViolation violation:e.getConstraintViolations()) {
			error.getViolations().add(
					new ViolationDTOResponse(violation.getPropertyPath().toString(),violation.getMessage(),violation.getInvalidValue().toString()));
		}
		return error;
	}

}
