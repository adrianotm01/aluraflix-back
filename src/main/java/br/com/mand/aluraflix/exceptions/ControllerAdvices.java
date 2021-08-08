package br.com.mand.aluraflix.exceptions;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerAdvices {

	
	@ExceptionHandler({NoSuchElementException.class,IllegalArgumentException.class})
	public ResponseEntity<String> tratamentoNaoEncontrado(){
		return new ResponseEntity<String>("Não encontrado", HttpStatus.NOT_FOUND);
	}

	
}
