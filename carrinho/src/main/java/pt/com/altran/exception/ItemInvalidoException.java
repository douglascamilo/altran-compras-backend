package pt.com.altran.exception;

public class ItemInvalidoException extends Exception {
	private static final long serialVersionUID = 8093068690118998677L;

	private ItemInvalidoException(final String mensagem) {
		super(mensagem);
	}

	public static ItemInvalidoException criarInvalido() {
		return new ItemInvalidoException("Item informado é inválido!");
	}

	public static ItemInvalidoException naoFoiPossivelValidar() {
		return new ItemInvalidoException("Erro ao invocar serviço para validar o item informado!");
	}
}