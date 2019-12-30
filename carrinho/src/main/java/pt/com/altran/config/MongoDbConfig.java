package pt.com.altran.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.MongoClient;

@Configuration
public class MongoDbConfig extends AbstractMongoConfiguration {
	@Autowired private Environment env;

	@Override
	public MongoClient mongoClient() {
		final String host = env.getProperty("spring.data.mongodb.host");
		final int port = env.getProperty("spring.data.mongodb.port", Integer.class);

		return new MongoClient(host, port);
	}

	@Override
	protected String getDatabaseName() {
		return env.getProperty("spring.data.mongodb.database");
	}
}