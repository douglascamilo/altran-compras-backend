package pt.com.altran.exception;

public class UsuarioJaCadastradoException extends Exception {
	private static final long serialVersionUID = 1085598092247373817L;

	public UsuarioJaCadastradoException() {
		super("Já existe um usuário cadastrado com o email informado.");
	}
}