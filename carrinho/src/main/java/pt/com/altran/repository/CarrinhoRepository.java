package pt.com.altran.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import pt.com.altran.model.Carrinho;

public interface CarrinhoRepository extends MongoRepository<Carrinho, String> {

	Optional<Carrinho> findByIdAndUsuarioId(String id, String usuarioId);

	Optional<Carrinho> findByUsuarioId(String usuarioId);
}