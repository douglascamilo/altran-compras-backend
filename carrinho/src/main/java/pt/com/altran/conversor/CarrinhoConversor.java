package pt.com.altran.conversor;

import org.springframework.stereotype.Component;

import pt.com.altran.dto.CarrinhoDTO;
import pt.com.altran.model.Carrinho;

@Component
public class CarrinhoConversor {

	public Carrinho converterParaModel(final CarrinhoDTO carrinho) {
		return Carrinho.builder()
				.usuarioId(carrinho.getUsuarioId())
				.itens(carrinho.getItens())
				.build();
	}

	public CarrinhoDTO converterParaDto(final Carrinho carrinho) {
		return CarrinhoDTO.builder()
				.id(carrinho.getId())
				.usuarioId(carrinho.getUsuarioId())
				.itens(carrinho.getItens())
				.build();
	}

	public void atualizarModel(final Carrinho carrinhoCadastrado, final CarrinhoDTO carrinho) {
		carrinhoCadastrado.setItens(carrinho.getItens());
	}

	public Carrinho converterParaModel(final String usuarioId) {
		return Carrinho.builder()
				.usuarioId(usuarioId)
				.build();
	}
}