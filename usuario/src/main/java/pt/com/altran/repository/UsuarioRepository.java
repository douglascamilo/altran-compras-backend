package pt.com.altran.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import pt.com.altran.model.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

	Optional<Usuario> findByEmail(String email);
}