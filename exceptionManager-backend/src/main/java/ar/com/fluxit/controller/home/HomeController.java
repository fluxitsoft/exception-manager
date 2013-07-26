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

import java.security.Principal;
import java.util.logging.ErrorManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import ar.com.fluxit.em.service.ExceptionManagerService;

@Controller
public class HomeController {

	@Autowired
	ExceptionManagerService exceptionManager;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Principal principal) {

		// try{
		// exceptionManager.simulateException();
		// }catch (Exception e) {
		// throw new RuntimeException(e);
		// }
		// return "";

		return principal != null ? "homeSignedIn" : "homeNotSignedIn";

	}

	@RequestMapping(value = "/error1", method = RequestMethod.GET)
	public String error1(Principal principal) {

		try {
			exceptionManager.simulateException();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return "";

		// return principal != null ? "homeSignedIn" : "homeNotSignedIn";

	}
	
	@RequestMapping(value = "/error2", method = RequestMethod.GET)
	public String error2(Principal principal) {

		try {
			exceptionManager.simulateDbException();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return "";

		// return principal != null ? "homeSignedIn" : "homeNotSignedIn";

	}
	
	
	
}
