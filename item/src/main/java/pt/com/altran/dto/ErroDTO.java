package pt.com.altran.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ErroDTO {
	private String mensagem;
}