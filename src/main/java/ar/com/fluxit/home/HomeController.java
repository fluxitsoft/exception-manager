package ar.com.fluxit.home;

import java.security.Principal;
import java.util.logging.ErrorManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import ar.com.fluxit.errorManager.service.ExceptionManagerService;

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
