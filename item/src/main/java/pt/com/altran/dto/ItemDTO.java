package pt.com.altran.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ItemDTO {
	private String id;
	private String nome;
	private BigDecimal valor;
}