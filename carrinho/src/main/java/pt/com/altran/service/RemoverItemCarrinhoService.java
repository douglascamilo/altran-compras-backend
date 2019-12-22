package pt.com.altran.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.com.altran.dto.EntradaRemoverItemCarrinhoDTO;
import pt.com.altran.dto.ItemDTO;
import pt.com.altran.exception.CarrinhoNaoEncontradoException;
import pt.com.altran.exception.ItemNaoRemovidoDoCarrinhoException;
import pt.com.altran.model.Carrinho;
import pt.com.altran.repository.CarrinhoRepository;

@Component
public class RemoverItemCarrinhoService implements CarrinhoService<EntradaRemoverItemCarrinhoDTO, Void> {
	@Autowired private CarrinhoRepository repository;

	@Override
	public Void executar(final EntradaRemoverItemCarrinhoDTO entrada)
			throws CarrinhoNaoEncontradoException, ItemNaoRemovidoDoCarrinhoException {

		final Carrinho encontrado = this.buscarComExcecao(repository, entrada.getCarrinho());
		encontrado.remover(
				ItemDTO.builder()
					.id(entrada.getItemId())
					.build());

		repository.save(encontrado);
		return null;
	}
}