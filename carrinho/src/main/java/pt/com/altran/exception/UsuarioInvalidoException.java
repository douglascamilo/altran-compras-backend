package pt.com.altran.exception;

public class UsuarioInvalidoException extends Exception {
	private static final long serialVersionUID = 1627538734142123569L;

	public UsuarioInvalidoException() {
		super("Usuário informado é inválido.");
	}
}