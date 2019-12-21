package pt.com.altran.conversor;

import org.springframework.stereotype.Component;

import pt.com.altran.dto.ItemDTO;
import pt.com.altran.model.Item;

@Component
public class ItemConversor {

	public ItemDTO converterParaDto(final Item item) {
		return ItemDTO.builder()
				.id(item.getId())
				.nome(item.getNome())
				.valor(item.getValor())
				.build();
	}

	public Item converterParaModel(final ItemDTO item) {
		return Item.builder()
				.nome(item.getNome())
				.valor(item.getValor())
				.build();
	}

	public void atualizarModel(final Item itemModel, final ItemDTO item) {
		itemModel.setNome(item.getNome());
		itemModel.setValor(item.getValor());
	}
}