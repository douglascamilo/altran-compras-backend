package pt.com.altran.client;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pt.com.altran.dto.ItemDTO;

@FeignClient(value = "item", decode404 = true)
public interface ItemClient {

	@GetMapping("/item/{id}")
	public Optional<ItemDTO> buscarPorId(@PathVariable("id") String itemId);
}