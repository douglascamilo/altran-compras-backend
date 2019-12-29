package pt.com.altran.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pt.com.altran.dto.ItemDTO;
import pt.com.altran.exception.ItemNaoEncontradoException;
import pt.com.altran.facade.ItemFacade;

@RestController
public class ItemController {
	@Autowired private ItemFacade facade;

	@GetMapping
	public ResponseEntity<List<ItemDTO>> buscarTodos() {
		final List<ItemDTO> itens = facade.buscarTodos();
		return ResponseEntity.ok(itens);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ItemDTO> buscarPorId(
			@PathVariable("id") final String id) throws ItemNaoEncontradoException {
		final ItemDTO item = facade.buscarPorId(id);
		return ResponseEntity.ok(item);
	}

	@GetMapping("/filtro/{filtro}")
	public ResponseEntity<List<ItemDTO>> buscarPorFiltro(
			@PathVariable("filtro") final String filtro) {

		final List<ItemDTO> itens = facade.buscarPorFiltro(filtro);
		return ResponseEntity.ok(itens);
	}

	@PostMapping
	public ResponseEntity<ItemDTO> salvar(
			@RequestBody final ItemDTO item,
			final UriComponentsBuilder uriBuilder) {

		final ItemDTO itemGravado = facade.salvar(item);
		final URI uri = uriBuilder.path("/item/{id}").buildAndExpand(itemGravado.getId()).toUri();

		return ResponseEntity.created(uri).body(itemGravado);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ItemDTO> atualizar(
			@PathVariable("id") final String id,
			@RequestBody final ItemDTO item) throws ItemNaoEncontradoException {

		final ItemDTO itemAtualizado = facade.atualizar(id, item);
		return ResponseEntity.ok(itemAtualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable("id") final String id) throws ItemNaoEncontradoException {
		facade.excluir(id);
		return ResponseEntity.ok().build();
	}
}