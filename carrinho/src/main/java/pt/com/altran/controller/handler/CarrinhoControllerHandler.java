package pt.com.altran.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pt.com.altran.dto.ErroDTO;
import pt.com.altran.exception.CarrinhoNaoEncontradoException;
import pt.com.altran.exception.ItemInvalidoException;
import pt.com.altran.exception.ItemNaoRemovidoDoCarrinhoException;
import pt.com.altran.exception.UsuarioInvalidoException;
import pt.com.altran.exception.UsuarioJaPossuiCarrinhoException;

@RestControllerAdvice
public class CarrinhoControllerHandler {

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler({
		CarrinhoNaoEncontradoException.class,
		ItemNaoRemovidoDoCarrinhoException.class
	})
	public ErroDTO tratarNotFound(final Exception exception) {
		return this.obterErro(exception);
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({
		UsuarioJaPossuiCarrinhoException.class,
		UsuarioInvalidoException.class,
		ItemInvalidoException.class
	})
	public ErroDTO tratarBadRequest(final Exception exception) {
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