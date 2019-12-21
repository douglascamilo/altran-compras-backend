package pt.com.altran.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import pt.com.altran.model.Item;

public interface ItemRepository extends MongoRepository<Item, String> {
}