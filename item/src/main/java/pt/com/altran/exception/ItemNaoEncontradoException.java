package pt.com.altran.exception;

public class ItemNaoEncontradoException extends Exception {
	private static final long serialVersionUID = -620345081447548748L;

	public ItemNaoEncontradoException() {
		super("Item não encontrado.");
	}
}