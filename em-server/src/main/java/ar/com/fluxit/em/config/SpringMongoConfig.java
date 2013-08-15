package ar.com.fluxit.em.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
public class SpringMongoConfig extends AbstractMongoConfiguration {
 
	
	@Value("${mongo.url}")
	private String mongoUrl;

	@Override
	public String getDatabaseName() {
		return "errorManager";
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		return new MongoClient(new MongoClientURI(mongoUrl));
	}
	
	@Override
	public MappingMongoConverter mappingMongoConverter() throws Exception {
		// TODO Auto-generated method stub
		MappingMongoConverter mappingMongoConverter =  super.mappingMongoConverter();
		mappingMongoConverter.setMapKeyDotReplacement("_");
		return mappingMongoConverter;
	}

	@Override
	public SimpleMongoDbFactory mongoDbFactory() throws Exception {
		// TODO Auto-generated method stub
		return super.mongoDbFactory();
	}
	
	
}
