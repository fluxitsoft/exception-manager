package ar.com.fluxit.em.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
public class SpringMongoConfig extends AbstractMongoConfiguration {
 
	
	
	@Value("${mongo.username}")
	private String mongoUsername;
		
	@Value("${mongo.password}")
	private String mongoPassword;
	
	@Value("${mongo.host}")
	private String mongoHost;
	
	@Value("${mongo.port}")
	private int mongoPort;
	
	@Value("${mongo.database}")
	private String database;

	@Override
	public String getDatabaseName() {
		return database;
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		return new MongoClient(mongoHost, mongoPort);
	}
	
	@Override
	protected UserCredentials getUserCredentials() {
		if(StringUtils.isNotEmpty(mongoUsername)){
			return new UserCredentials(mongoUsername, mongoPassword);
		}else{
			return null;
		}
	}

	@Override
	public MappingMongoConverter mappingMongoConverter() throws Exception {
		// TODO Auto-generated method stub
		MappingMongoConverter mappingMongoConverter =  super.mappingMongoConverter();
		mappingMongoConverter.setMapKeyDotReplacement("_");
		return mappingMongoConverter;
	}


	
	
	
	
}
