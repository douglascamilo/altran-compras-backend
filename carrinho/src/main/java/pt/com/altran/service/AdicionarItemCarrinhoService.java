package pt.com.altran.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.com.altran.dto.AdicionarItemCarrinhoDTO;
import pt.com.altran.dto.ItemDTO;
import pt.com.altran.exception.CarrinhoNaoEncontradoException;
import pt.com.altran.model.Carrinho;
import pt.com.altran.repository.CarrinhoRepository;

@Component
public class AdicionarItemCarrinhoService implements CarrinhoService<AdicionarItemCarrinhoDTO, Void> {
	@Autowired private CarrinhoRepository repository;

	@Override
	public Void executar(final AdicionarItemCarrinhoDTO entrada)
			throws CarrinhoNaoEncontradoException {

		final ItemDTO item = entrada.getItem();

		final Carrinho carrinhoEncontrado = this.buscarComExcecao(repository, entrada.getCarrinho());
		carrinhoEncontrado.adicionar(item);

		repository.save(carrinhoEncontrado);
		return null;
	}
}