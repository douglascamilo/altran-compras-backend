package pt.com.altran.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.com.altran.conversor.CarrinhoConversor;
import pt.com.altran.dto.CarrinhoDTO;
import pt.com.altran.exception.CarrinhoNaoEncontradoException;
import pt.com.altran.model.Carrinho;
import pt.com.altran.repository.CarrinhoRepository;

@Component
public class BuscarCarrinhoService implements CarrinhoService<String, CarrinhoDTO> {
	@Autowired private CarrinhoRepository repository;
	@Autowired private CarrinhoConversor conversor;

	@Override
	public CarrinhoDTO executar(final String usuarioId) throws CarrinhoNaoEncontradoException {
		final Carrinho carrinho = this.buscarPorIdUsuarioComExcecao(repository, usuarioId);
		return conversor.converterParaDto(carrinho);
	}
}