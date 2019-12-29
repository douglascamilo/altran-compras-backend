package pt.com.altran.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.com.altran.conversor.ItemConversor;
import pt.com.altran.dto.ItemDTO;
import pt.com.altran.exception.ItemNaoEncontradoException;
import pt.com.altran.model.Item;
import pt.com.altran.repository.ItemRepository;

@Component
public class ItemService {
	@Autowired private ItemRepository repository;
	@Autowired private ItemConversor conversor;

	public List<Item> buscarTodos() {
		return repository.findAll();
	}

	public List<Item> buscarPorFiltro(final String filtro) {
		return repository.findByNome(filtro);
	}

	public Item buscarPorId(final String id) throws ItemNaoEncontradoException {
		return buscarPorIdComExcecao(id);
	}

	public Item salvar(final Item item) {
		return repository.save(item);
	}

	public Item atualizar(final String id, final ItemDTO item) throws ItemNaoEncontradoException {
		final Item itemCadastrado = this.buscarPorIdComExcecao(id);
		conversor.atualizarModel(itemCadastrado, item);

		return this.salvar(itemCadastrado);
	}

	public void excluir(final String id) throws ItemNaoEncontradoException {
		this.buscarPorIdComExcecao(id);
		repository.deleteById(id);
	}

	private Item buscarPorIdComExcecao(final String id) throws ItemNaoEncontradoException {
		return repository.findById(id)
				.orElseThrow(ItemNaoEncontradoException::new);
	}
}