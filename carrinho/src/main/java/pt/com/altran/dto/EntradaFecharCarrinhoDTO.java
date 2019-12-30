package pt.com.altran.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EntradaFecharCarrinhoDTO {
	private String carrinhoId;
	private String usuarioId;
}