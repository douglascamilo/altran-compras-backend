package pt.com.altran.facade;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.com.altran.conversor.UsuarioConversor;
import pt.com.altran.dto.UsuarioDTO;
import pt.com.altran.exception.UsuarioJaCadastradoException;
import pt.com.altran.exception.UsuarioNaoEncontradoException;
import pt.com.altran.model.Usuario;
import pt.com.altran.service.UsuarioService;

@Service
public class UsuarioFacade {
	@Autowired private UsuarioService service;
	@Autowired private UsuarioConversor conversor;

	public UsuarioDTO salvar(final UsuarioDTO usuarioDto) throws UsuarioJaCadastradoException {
		final Usuario usuarioNovo = conversor.converterParaModel(usuarioDto);
		final Usuario usuarioGravado = service.salvar(usuarioNovo);

		return conversor.converterParaDto(usuarioGravado);
	}

	public UsuarioDTO buscarPorId(final String id) throws UsuarioNaoEncontradoException {
		final Usuario usuario = service.buscarPorId(id);
		return conversor.converterParaDto(usuario);
	}

	public UsuarioDTO atualizar(final String id, final UsuarioDTO usuario) throws UsuarioNaoEncontradoException {
		final Usuario usuarioAtualizado = service.atualizar(id, usuario);
		return conversor.converterParaDto(usuarioAtualizado);
	}

	public void excluir(final String id) throws UsuarioNaoEncontradoException {
		service.excluir(id);
	}

	public List<UsuarioDTO> buscarTodos() {
		final List<Usuario> usuarios = service.buscarTodos();
		return usuarios.stream()
				.map(conversor::converterParaDto)
				.collect(Collectors.toList());
	}
}