package pt.com.altran.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErroDTO {
	private final String mensagem;
}