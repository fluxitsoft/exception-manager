package ar.com.fluxit.errorManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ar.com.fluxit.errorManager.sourceCode.SourceCodeProvider;

@Controller
public class SourceCodeController {

	@Autowired
	private SourceCodeProvider sourceCodeProvider;

	@RequestMapping(value = "sourceCode", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public String sourceCode(@RequestParam String className,
			@RequestParam String fileName) {

		return sourceCodeProvider.getSourceCode(className, fileName);

	}
}
