package pt.com.altran.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EntradaRemoverItemCarrinhoDTO {
	private CarrinhoDTO carrinho;
	private String itemId;
}