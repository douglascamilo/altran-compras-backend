package pt.com.altran.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pt.com.altran.dto.ErroDTO;
import pt.com.altran.exception.ItemNaoEncontradoException;

@RestControllerAdvice
public class ItemControllerHandler {

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler({
		ItemNaoEncontradoException.class
	})
	public ErroDTO tratarItemNaoEncontradoException(final Exception exception) {
		return this.obterErro(exception);
	}

	private ErroDTO obterErro(final Exception exception) {
		return ErroDTO.builder()
				.mensagem(exception.getMessage())
				.build();
	}
}