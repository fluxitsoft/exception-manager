package ar.com.fluxit.em.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;

@Data
@Entity
public class ExceptionDescriptor {
	@Id
	@GeneratedValue
	private long id;

	@Column(unique = true)
	private String className;

	private String version;
	
	@Column
	@Lob
	private String description;

	private String javaDoc;

}
