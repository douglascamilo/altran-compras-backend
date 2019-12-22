package pt.com.altran.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.com.altran.conversor.CarrinhoConversor;
import pt.com.altran.dto.CarrinhoDTO;
import pt.com.altran.exception.UsuarioJaPossuiCarrinhoException;
import pt.com.altran.model.Carrinho;
import pt.com.altran.repository.CarrinhoRepository;

@Component
public class CriarCarrinhoService implements CarrinhoService<String, CarrinhoDTO> {
	@Autowired private CarrinhoRepository repository;
	@Autowired private CarrinhoConversor conversor;

	@Override
	public CarrinhoDTO executar(final String usuarioId)
			throws UsuarioJaPossuiCarrinhoException {

		this.buscarComExcecao(repository, usuarioId);

		final Carrinho carrinho = conversor.converterParaModel(usuarioId);
		final Carrinho carrinhoGravado = repository.save(carrinho);

		return conversor.converterParaDto(carrinhoGravado);
	}
}