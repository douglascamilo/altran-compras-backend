package pt.com.altran.client;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pt.com.altran.dto.UsuarioDTO;

@FeignClient(name = "usuario")
public interface UsuarioClient {

	@GetMapping("/usuario/{id}")
	public Optional<UsuarioDTO> buscarPorId(@PathVariable("id") final String id);
}