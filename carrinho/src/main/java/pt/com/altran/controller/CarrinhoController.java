package pt.com.altran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping("/buscar")
	public ResponseEntity<CarrinhoDTO> buscarPorUsuarioId(
			@RequestParam("usuarioId") final String usuarioId) throws CarrinhoNaoEncontradoException {

		final CarrinhoDTO carrinhoEncontrado = facade.buscar(usuarioId);
		return ResponseEntity.ok(carrinhoEncontrado);
	}

	@PostMapping("/criar")
	public ResponseEntity<CarrinhoDTO> criar(
			@RequestParam("usuarioId") final String usuarioId) throws UsuarioJaPossuiCarrinhoException, UsuarioInvalidoException {

		final CarrinhoDTO carrinhoGravado = facade.criar(usuarioId);
		return ResponseEntity.created(null).body(carrinhoGravado);
	}

	@PutMapping("/gravar-item")
	public ResponseEntity<Void> gravarItem(
			@RequestParam("carrinhoId") final String carrinhoId,
			@RequestParam("usuarioId") final String usuarioId,
			@RequestBody final ItemDTO item)
			throws CarrinhoNaoEncontradoException, ItemInvalidoException, UsuarioInvalidoException {

		final CarrinhoDTO carrinho = CarrinhoDTO.builder()
				.id(carrinhoId)
				.usuarioId(usuarioId)
				.build();

		facade.gravarItem(carrinho, item);
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