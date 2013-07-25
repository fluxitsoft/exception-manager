package ar.com.fluxit.em.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;


import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
public class SpringMongoConfig extends AbstractMongoConfiguration {
 
	@Override
	public String getDatabaseName() {
		return "errorManager";
	}
 
	@Override
	@Bean
	public Mongo mongo() throws Exception {
		return new MongoClient("127.0.0.1");
	}
	
	@Override
	public MappingMongoConverter mappingMongoConverter() throws Exception {
		// TODO Auto-generated method stub
		MappingMongoConverter mappingMongoConverter =  super.mappingMongoConverter();
		mappingMongoConverter.setMapKeyDotReplacement("_");
		return mappingMongoConverter;
	}
}
