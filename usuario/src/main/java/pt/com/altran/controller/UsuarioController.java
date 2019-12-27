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

import pt.com.altran.dto.UsuarioDTO;
import pt.com.altran.exception.UsuarioJaCadastradoException;
import pt.com.altran.exception.UsuarioNaoEncontradoException;
import pt.com.altran.facade.UsuarioFacade;

@RestController
public class UsuarioController {
	@Autowired private UsuarioFacade facade;

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> buscarTodos() {
		final List<UsuarioDTO> usuarios = facade.buscarTodos();
		return ResponseEntity.ok(usuarios);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable("id") final String id)
			throws UsuarioNaoEncontradoException {

		final UsuarioDTO usuario = facade.buscarPorId(id);
		return ResponseEntity.ok(usuario);
	}

	@PostMapping
	public ResponseEntity<UsuarioDTO> salvar(
			@RequestBody final UsuarioDTO usuario,
			final UriComponentsBuilder uriBuilder) throws UsuarioJaCadastradoException {

		final UsuarioDTO usuarioGravado = facade.salvar(usuario);
		final URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuarioGravado.getId()).toUri();

		return ResponseEntity.created(uri).body(usuarioGravado);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> atualizar(
			@PathVariable("id") final String id,
			@RequestBody final UsuarioDTO usuario) throws UsuarioNaoEncontradoException {

		final UsuarioDTO usuarioAtualizado = facade.atualizar(id, usuario);
		return ResponseEntity.ok(usuarioAtualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable("id") final String id) throws UsuarioNaoEncontradoException {
		facade.excluir(id);
		return ResponseEntity.ok().build();
	}
}