package pt.com.altran.service;

import java.util.Optional;

import pt.com.altran.dto.CarrinhoDTO;
import pt.com.altran.exception.CarrinhoNaoEncontradoException;
import pt.com.altran.exception.UsuarioJaPossuiCarrinhoException;
import pt.com.altran.model.Carrinho;
import pt.com.altran.repository.CarrinhoRepository;

public interface CarrinhoService<ENTRADA, SAIDA> {

	SAIDA executar(ENTRADA entrada) throws Exception;

	default Carrinho buscarComExcecao(final CarrinhoRepository repository, final CarrinhoDTO carrinho)
			throws CarrinhoNaoEncontradoException {

		return repository.findByIdAndUsuarioId(carrinho.getId(), carrinho.getUsuarioId())
				.orElseThrow(CarrinhoNaoEncontradoException::new);
	}

	default void buscarComExcecao(final CarrinhoRepository repository, final String usuarioId)
			throws UsuarioJaPossuiCarrinhoException {

		final Optional<Carrinho> carrinho = repository.findByUsuarioId(usuarioId);

		if (carrinho.isPresent()) {
			throw new UsuarioJaPossuiCarrinhoException();
		}
	}
}