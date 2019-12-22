package pt.com.altran.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.com.altran.client.ItemClient;
import pt.com.altran.dto.ItemDTO;
import pt.com.altran.exception.ItemInvalidoException;

@Component
public class ItemService {
	@Autowired private ItemClient client;

	public ItemDTO buscarPorId(final String id) throws ItemInvalidoException {
		try {
			return client.buscarPorId(id)
				.orElseThrow(ItemInvalidoException::criarInvalido);
		} catch (final RuntimeException e) {
			throw ItemInvalidoException.naoFoiPossivelValidar();
		}
	}
}