package ar.com.fluxit.em.model;

import org.springframework.data.annotation.Id;
import ar.com.fluxit.em.model.Error;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
// Todo, ver si se puede definir declarativamente asi no replico las entidades
// (no tengo gansa de user DTOs).
public class ErrorDocument extends Error {

	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
