package pt.com.altran.facade;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.com.altran.conversor.ItemConversor;
import pt.com.altran.dto.ItemDTO;
import pt.com.altran.exception.ItemNaoEncontradoException;
import pt.com.altran.model.Item;
import pt.com.altran.service.ItemService;

@Service
public class ItemFacade {
	@Autowired private ItemService service;
	@Autowired private ItemConversor conversor;

	public List<ItemDTO> buscarTodos() {
		final List<Item> itens = service.buscarTodos();
		return this.converterItensParaDto(itens);
	}

	public List<ItemDTO> buscarPorFiltro(final String filtro) {
		final List<Item> itens = service.buscarPorFiltro(filtro);
		return this.converterItensParaDto(itens);
	}

	public ItemDTO buscarPorId(final String id) throws ItemNaoEncontradoException {
		final Item itemCadastrado = service.buscarPorId(id);
		return conversor.converterParaDto(itemCadastrado);
	}

	public ItemDTO salvar(final ItemDTO item) {
		final Item itemNovo = conversor.converterParaModel(item);
		final Item itemGravado = service.salvar(itemNovo);

		return conversor.converterParaDto(itemGravado);
	}

	public ItemDTO atualizar(final String id, final ItemDTO item) throws ItemNaoEncontradoException {
		final Item itemAtualizado = service.atualizar(id, item);
		return conversor.converterParaDto(itemAtualizado);
	}

	public void excluir(final String id) throws ItemNaoEncontradoException {
		service.excluir(id);
	}

	private List<ItemDTO> converterItensParaDto(final List<Item> itens) {
		return itens.stream()
				.map(conversor::converterParaDto)
				.collect(Collectors.toList());
	}
}