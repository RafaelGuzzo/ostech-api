package br.com.ostech.api.exceptionhandler;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErroSaidaDto handle(MethodArgumentNotValidException ex) {
		ErroSaidaDto erroSaidaDto = new ErroSaidaDto("Um ou mais campos estão invalidos!");

		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

		for (FieldError fieldError : fieldErrors) {
			String nome = fieldError.getField();
			String mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());

			erroSaidaDto.adicionarCampoComErro(nome, mensagem);
		}

		return erroSaidaDto;
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ConstraintViolationException.class)
	public ErroSaidaDto recursoNaoEncontrado(ConstraintViolationException ex) {

		return new ErroSaidaDto(ex.getMessage());
	}
}
