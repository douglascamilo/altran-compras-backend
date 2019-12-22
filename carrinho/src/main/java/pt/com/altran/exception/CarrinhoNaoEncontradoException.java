package pt.com.altran.exception;

public class CarrinhoNaoEncontradoException extends Exception {
	private static final long serialVersionUID = -4612975605341569259L;

	public CarrinhoNaoEncontradoException() {
		super("Carrinho não encontrado!");
	}
}