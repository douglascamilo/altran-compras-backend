package pt.com.altran.exception;

public class UsuarioJaPossuiCarrinhoException extends Exception {
	private static final long serialVersionUID = -7599396654414414522L;

	public UsuarioJaPossuiCarrinhoException() {
		super("Usuário já possui carrinho!");
	}
}