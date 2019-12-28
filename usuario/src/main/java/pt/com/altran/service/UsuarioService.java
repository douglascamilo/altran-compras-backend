package pt.com.altran.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.com.altran.conversor.UsuarioConversor;
import pt.com.altran.dto.UsuarioDTO;
import pt.com.altran.exception.UsuarioJaCadastradoException;
import pt.com.altran.exception.UsuarioNaoEncontradoException;
import pt.com.altran.model.Usuario;
import pt.com.altran.repository.UsuarioRepository;

@Component
public class UsuarioService {
	@Autowired private UsuarioRepository repository;
	@Autowired private UsuarioConversor conversor;

	public Usuario atualizar(final String id, final UsuarioDTO usuario) throws UsuarioNaoEncontradoException {
		final Usuario usuarioCadastrado = this.buscaPorIdComExcecao(id);
		conversor.atualizarModel(usuarioCadastrado, usuario);

		return repository.save(usuarioCadastrado);
	}

	public Usuario salvar(final Usuario usuario) throws UsuarioJaCadastradoException {
		final boolean emailJaCadastrado = repository.findByEmail(usuario.getEmail()).isPresent();

		if (emailJaCadastrado) {
			throw new UsuarioJaCadastradoException();
		}

		return repository.save(usuario);
	}

	public Usuario buscarPorId(final String id) throws UsuarioNaoEncontradoException {
		return this.buscaPorIdComExcecao(id);
	}

	public void excluir(final String id) throws UsuarioNaoEncontradoException {
		this.buscaPorIdComExcecao(id);

		repository.deleteById(id);
	}

	private Usuario buscaPorIdComExcecao(final String id) throws UsuarioNaoEncontradoException {
		return repository.findById(id).orElseThrow(UsuarioNaoEncontradoException::new);
	}

	public List<Usuario> buscarTodos() {
		return repository.findAll();
	}

	public List<Usuario> buscarPorFiltro(final String filtro) {
		return repository.findByNome(filtro);
	}
}