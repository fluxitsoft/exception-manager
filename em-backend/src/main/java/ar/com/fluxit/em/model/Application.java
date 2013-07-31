package ar.com.fluxit.em.model;


import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Application {

	@Id
	private String id;


	private String name;
	
	

	private String sourceCodeServiceUrl;



	private String key;

}
