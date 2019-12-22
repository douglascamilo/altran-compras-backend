package pt.com.altran.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pt.com.altran.dto.ErroDTO;
import pt.com.altran.exception.UsuarioJaCadastradoException;
import pt.com.altran.exception.UsuarioNaoEncontradoException;

@RestControllerAdvice
public class UsuarioControllerHandler {

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler({
		UsuarioNaoEncontradoException.class,
	})
	public ErroDTO tratarUsuarioNaoEncontradoException(final Exception exception) {
		return this.obterErro(exception);
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({
		UsuarioJaCadastradoException.class
	})
	public ErroDTO tratarUsuarioJaCadastradoException(final Exception exception) {
		return this.obterErro(exception);
	}

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({
		Exception.class
	})
	public ErroDTO tratarInternalServerError(final Exception exception) {
		return this.obterErro(exception);
	}

	private ErroDTO obterErro(final Exception exception) {
		return ErroDTO.builder()
				.mensagem(exception.getMessage())
				.build();
	}
}