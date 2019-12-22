package pt.com.altran.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.com.altran.dto.ItemDTO;
import pt.com.altran.exception.ItemNaoRemovidoDoCarrinhoException;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "carrinho")
public class Carrinho {
	@Id
	private String id;
	private String usuarioId;
	@Builder.Default
	private List<ItemDTO> itens = new ArrayList<>();

	@Version
	private Long versao;

	public void adicionar(final ItemDTO item) {
		final ItemDTO itemEncontrado = itens.stream()
			.filter(i -> i.equals(item))
			.findFirst()
			.orElse(null);

		if (itemEncontrado != null) {
			itemEncontrado.setQuantidade(item.getQuantidade());
		} else {
			itens.add(item);
		}
	}

	public void remover(final ItemDTO item) throws ItemNaoRemovidoDoCarrinhoException {
		final boolean itemRemovido = itens.remove(item);

		if (!itemRemovido) {
			throw new ItemNaoRemovidoDoCarrinhoException();
		}
	}
}