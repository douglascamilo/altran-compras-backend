package pt.com.altran.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import pt.com.altran.model.Item;

public interface ItemRepository extends MongoRepository<Item, String> {

	@Query("{'nome': { $regex: ?0, $options: 'i' }})")
	List<Item> findByNome(String filtro);
}