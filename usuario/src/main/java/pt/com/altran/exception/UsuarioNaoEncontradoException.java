package pt.com.altran.exception;

public class UsuarioNaoEncontradoException extends Exception {
	private static final long serialVersionUID = -7907796926168885682L;

	public UsuarioNaoEncontradoException() {
		super("Nenhum usuário encontrado.");
	}
}