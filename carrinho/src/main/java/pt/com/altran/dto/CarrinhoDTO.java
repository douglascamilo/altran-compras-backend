package pt.com.altran.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarrinhoDTO {
	private String id;
	private String usuarioId;
	@Builder.Default
	private List<ItemDTO> itens = new ArrayList<>();
}