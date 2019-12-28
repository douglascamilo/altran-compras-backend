package pt.com.altran.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import pt.com.altran.model.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

	Optional<Usuario> findByEmail(String email);

	@Query("{'nome': { $regex: ?0, $options: 'i' }})")
	List<Usuario> findByNome(String filtro);
}