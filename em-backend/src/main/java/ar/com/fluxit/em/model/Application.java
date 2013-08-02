/*******************************************************************************
 * FluxIT 
 * Copyright (c) 2013 
 * Argentina
 * 
 * This is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of 
 * the License, or (at your option) any later version.
 * 
 * This is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General 
 * Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

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



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSourceCodeServiceUrl() {
		return sourceCodeServiceUrl;
	}



	public void setSourceCodeServiceUrl(String sourceCodeServiceUrl) {
		this.sourceCodeServiceUrl = sourceCodeServiceUrl;
	}



	public String getKey() {
		return key;
	}



	public void setKey(String key) {
		this.key = key;
	}

}
