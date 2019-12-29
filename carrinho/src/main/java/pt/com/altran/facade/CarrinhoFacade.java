package pt.com.altran.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.com.altran.dto.AdicionarItemCarrinhoDTO;
import pt.com.altran.dto.CarrinhoDTO;
import pt.com.altran.dto.EntradaRemoverItemCarrinhoDTO;
import pt.com.altran.dto.ItemDTO;
import pt.com.altran.exception.CarrinhoNaoEncontradoException;
import pt.com.altran.exception.ItemInvalidoException;
import pt.com.altran.exception.ItemNaoRemovidoDoCarrinhoException;
import pt.com.altran.exception.UsuarioInvalidoException;
import pt.com.altran.exception.UsuarioJaPossuiCarrinhoException;
import pt.com.altran.service.AdicionarItemCarrinhoService;
import pt.com.altran.service.BuscarCarrinhoService;
import pt.com.altran.service.CriarCarrinhoService;
import pt.com.altran.service.ItemService;
import pt.com.altran.service.RemoverItemCarrinhoService;
import pt.com.altran.service.UsuarioService;

@Service
public class CarrinhoFacade {
	@Autowired private CriarCarrinhoService criarCarrinhoService;
	@Autowired private AdicionarItemCarrinhoService adicionarItemCarrinhoService;
	@Autowired private RemoverItemCarrinhoService removerItemCarrinhoService;
	@Autowired private BuscarCarrinhoService buscarCarrinhoService;

	@Autowired private UsuarioService usuarioService;
	@Autowired private ItemService itemService;

	public CarrinhoDTO buscar(final String usuarioId) throws CarrinhoNaoEncontradoException {
		return buscarCarrinhoService.executar(usuarioId);
	}

	public CarrinhoDTO criar(final String usuarioId) throws UsuarioJaPossuiCarrinhoException, UsuarioInvalidoException {
		usuarioService.isValido(usuarioId);
		return criarCarrinhoService.executar(usuarioId);
	}

	public void gravarItem(final CarrinhoDTO carrinho, final ItemDTO item)
			throws CarrinhoNaoEncontradoException, ItemInvalidoException, UsuarioInvalidoException {

		usuarioService.isValido(carrinho.getUsuarioId());
		final ItemDTO itemCarregado = this.obterItem(item);

		final AdicionarItemCarrinhoDTO entrada = AdicionarItemCarrinhoDTO.builder()
			.carrinho(carrinho)
			.item(itemCarregado)
			.build();

		adicionarItemCarrinhoService.executar(entrada);
	}

	public void removerItem(final CarrinhoDTO carrinho, final String itemId)
			throws CarrinhoNaoEncontradoException, ItemNaoRemovidoDoCarrinhoException, UsuarioInvalidoException,
				ItemInvalidoException {

		usuarioService.isValido(carrinho.getUsuarioId());
		itemService.buscarPorId(itemId);

		final EntradaRemoverItemCarrinhoDTO entrada = EntradaRemoverItemCarrinhoDTO.builder()
				.carrinho(carrinho)
				.itemId(itemId)
				.build();

		removerItemCarrinhoService.executar(entrada);
	}

	private ItemDTO obterItem(final ItemDTO item) throws ItemInvalidoException {
		final ItemDTO itemRetornado = itemService.buscarPorId(item.getId());
		itemRetornado.setQuantidade(item.getQuantidade());

		return itemRetornado;
	}
}