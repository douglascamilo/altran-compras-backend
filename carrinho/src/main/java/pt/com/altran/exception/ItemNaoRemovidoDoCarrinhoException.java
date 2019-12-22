package pt.com.altran.exception;

public class ItemNaoRemovidoDoCarrinhoException extends Exception {
	private static final long serialVersionUID = -3021368646811980595L;

	public ItemNaoRemovidoDoCarrinhoException() {
		super("Não existe nenhum item com o id informado no carrinho");
	}
}