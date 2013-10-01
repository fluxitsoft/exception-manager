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
package ar.com.fluxit.controller.home;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "home";

	}
	
	
	
	@RequestMapping(value = "/errorRequest", method = RequestMethod.GET)
	public String errorRequest() {
		int i = 1/0;
		return "home";

	}
	
	@RequestMapping(value = "/errorRequest2", method = RequestMethod.GET)
	public String errorRequest2() {
		try{
			  EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
			    EmbeddedDatabase db = builder.build();
			    // do stuff against the db (EmbeddedDatabase extends javax.sql.DataSource)
			    db.shutdown();
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}
		return "home";

	}
	
	
}
