package pt.com.altran.conversor;

import org.springframework.stereotype.Component;

import pt.com.altran.dto.UsuarioDTO;
import pt.com.altran.model.Usuario;

@Component
public class UsuarioConversor {

	public Usuario converterParaModel(final UsuarioDTO usuario) {
		return Usuario.builder()
				.id(usuario.getId())
				.nome(usuario.getNome())
				.email(usuario.getEmail())
				.build();
	}

	public UsuarioDTO converterParaDto(final Usuario usuario) {
		return UsuarioDTO.builder()
				.id(usuario.getId())
				.email(usuario.getEmail())
				.nome(usuario.getNome())
				.build();
	}

	public void atualizarModel(final Usuario usuarioModel, final UsuarioDTO usuario) {
		usuarioModel.setEmail(usuario.getEmail());
		usuarioModel.setNome(usuario.getNome());
	}
}