package pt.com.altran.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pt.com.altran.dto.CarrinhoDTO;
import pt.com.altran.dto.ItemDTO;
import pt.com.altran.exception.CarrinhoNaoEncontradoException;
import pt.com.altran.exception.ItemInvalidoException;
import pt.com.altran.exception.ItemNaoRemovidoDoCarrinhoException;
import pt.com.altran.exception.UsuarioInvalidoException;
import pt.com.altran.exception.UsuarioJaPossuiCarrinhoException;
import pt.com.altran.facade.CarrinhoFacade;

@RestController
public class CarrinhoController {
	@Autowired private CarrinhoFacade facade;

	@PostMapping("/criar")
	public ResponseEntity<CarrinhoDTO> criar(
			@RequestParam("usuarioId") final String usuarioId,
			final UriComponentsBuilder uriBuilder) throws UsuarioJaPossuiCarrinhoException, UsuarioInvalidoException {

		final CarrinhoDTO carrinhoGravado = facade.criar(usuarioId);
		final URI uri = uriBuilder.path("/carrinho/{id}").buildAndExpand(carrinhoGravado.getId()).toUri();

		return ResponseEntity.created(uri).body(carrinhoGravado);
	}

	@PutMapping("/adicionar-item")
	public ResponseEntity<Void> adicionarItem(
			@RequestParam("carrinhoId") final String carrinhoId,
			@RequestParam("usuarioId") final String usuarioId,
			@RequestBody final ItemDTO item)
			throws CarrinhoNaoEncontradoException, ItemInvalidoException, UsuarioInvalidoException {

		final CarrinhoDTO carrinho = CarrinhoDTO.builder()
				.id(carrinhoId)
				.usuarioId(usuarioId)
				.build();

		facade.adicionarItem(carrinho, item);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/remover-item/{itemId}")
	public ResponseEntity<Void> removerItem(
			@RequestParam("carrinhoId") final String carrinhoId,
			@RequestParam("usuarioId") final String usuarioId,
			@PathVariable("itemId") final String itemId) throws CarrinhoNaoEncontradoException,
			ItemNaoRemovidoDoCarrinhoException, UsuarioInvalidoException, ItemInvalidoException {

		final CarrinhoDTO carrinho = CarrinhoDTO.builder()
				.id(carrinhoId)
				.usuarioId(usuarioId)
				.build();

		facade.removerItem(carrinho, itemId);
		return ResponseEntity.ok().build();
	}
}