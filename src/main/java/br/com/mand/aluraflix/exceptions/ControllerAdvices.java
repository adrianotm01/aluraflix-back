package br.com.mand.aluraflix.exceptions;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerAdvices {

	
	@ExceptionHandler({NoSuchElementException.class,IllegalArgumentException.class})
	public ResponseEntity<String> tratamentoNaoEncontrado(){
		return new ResponseEntity<String>("Não encontrado", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<String> tratamentoExcecaoDeValidarOObjeto(MethodArgumentNotValidException ex){
		String mensagensDeErro = ex.getFieldErrors().stream().map(err -> {
			return String.format("%s: Este campo não pode estar em branco ", err.getField());
		}).collect(Collectors.joining());
		return new ResponseEntity<String>(mensagensDeErro, HttpStatus.NOT_FOUND);
	}

	
}
