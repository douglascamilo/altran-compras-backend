package pt.com.altran.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.com.altran.client.UsuarioClient;
import pt.com.altran.exception.UsuarioInvalidoException;

@Component
public class UsuarioService {
	@Autowired private UsuarioClient client;

	public void isValido(final String usuarioId) throws UsuarioInvalidoException {
		try {
			client.buscarPorId(usuarioId)
				.orElseThrow(UsuarioInvalidoException::new);
		} catch (final RuntimeException e) {
			throw new UsuarioInvalidoException();
		}
	}
}