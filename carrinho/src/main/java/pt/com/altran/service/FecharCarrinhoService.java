package pt.com.altran.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import pt.com.altran.conversor.CarrinhoConversor;
import pt.com.altran.dto.CarrinhoDTO;
import pt.com.altran.dto.EntradaFecharCarrinhoDTO;
import pt.com.altran.repository.CarrinhoRepository;

@Component
public class FecharCarrinhoService implements CarrinhoService<EntradaFecharCarrinhoDTO, CarrinhoDTO> {
	@Autowired private CarrinhoRepository repository;
	@Autowired private MongoTemplate mongoTemplate;
	@Autowired private CarrinhoConversor conversor;

	@Override
	public CarrinhoDTO executar(final EntradaFecharCarrinhoDTO entrada) {
//		final Aggregation aggregations = Aggregation.newAggregation(
//			Aggregation.match(
//					Criteria.where("_id").is(entrada.getCarrinhoId())
//						.and("usuarioId").is(entrada.getUsuarioId())),
//
//			Aggregation.unwind("$itens"),
//
//			new AggregationOperation() {
//
//				@Override
//				public Document toDocument(final AggregationOperationContext aoc) {
//					return new Document("$addFields", new Document("$simple.name",""));
//				}
//			}
//		);
//
//		final List<Document> mappedResults = mongoTemplate.aggregate(aggregations, "carrinho", Document.class)
//			.getMappedResults();

//		final Document document = mongoTemplate.executeCommand("db.carrinho.aggregate([{ $match: {_id: ObjectId('5e0831ed11b1a45ab799ea24'),usuarioId: '5e06e64d7ca926174eee371c'}},{ $unwind: \"$itens\" },{ $addFields: { \"itens.valor_total\": { $multiply: [\"$itens.quantidade\", \"$itens.valor\"] }}},{ $sort: {\"itens.nome\": -1,\"itens.valor\": -1}},{ $group: {_id: \"$_id\",usuarioId: { $first: \"$usuarioId\" },valor_total: { $sum: \"$itens.valor_total\"},itens: { $push: \"$itens\" }}}]}");

		return null;
	}
}