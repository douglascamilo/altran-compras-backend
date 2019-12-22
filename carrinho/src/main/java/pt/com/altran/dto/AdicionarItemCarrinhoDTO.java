package pt.com.altran.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AdicionarItemCarrinhoDTO {
	private CarrinhoDTO carrinho;
	private ItemDTO item;
}