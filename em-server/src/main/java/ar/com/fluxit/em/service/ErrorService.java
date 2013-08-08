package ar.com.fluxit.em.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import ar.com.fluxit.em.controller.ErrorDetail;
import ar.com.fluxit.em.model.Application;
import ar.com.fluxit.em.model.Error;
import ar.com.fluxit.em.model.ErrorDocument;
import ar.com.fluxit.em.model.MemoryContext;

@Service
public class ErrorService {

	@Autowired
	ApplicationService applicationService;
	
	@Autowired
	ErrorIndexService errorIndexService;

	@Autowired
	MongoOperations mongoOperations;

	public String addError(ar.com.fluxit.em.model.ErrorDocument error) {
		error.setTime(new Date());
		
		mongoOperations.insert(error, "errors");
		errorIndexService.add(error);
		
		CometService.instance.publish("admin", toErrorDetail(error));
		
		return error.getId();
	}
	


	public Error getError(String errorId) {
		ErrorDocument errorDocument = mongoOperations.findById(errorId,
				ErrorDocument.class, "errors");
		if(errorDocument.getMemoryContext() == null){
			errorDocument.setMemoryContext(new MemoryContext());
		}
		
		
		Application application = applicationService.getApplication(errorDocument.getApplicationKey());
		
		if(application != null){
			errorDocument.setSourceCodeUrlProvider(application.getSourceCodeServiceUrl());
		}
		return errorDocument;
	}

	public List<ErrorDetail> getErrorDetails() {

		List<ErrorDetail> errorDetails = new ArrayList<>();
		Query query = new Query();
		query.with(new Sort(Direction.DESC, "time")).limit(100);
		List<ErrorDocument> errors = mongoOperations.find(query,
				ErrorDocument.class, "errors");

		for (ErrorDocument errorDocument : errors) {
			ErrorDetail errorDetail = toErrorDetail(errorDocument);
			errorDetails.add(errorDetail);
		}
		
		return errorDetails;
	}


	private ErrorDetail toErrorDetail(ErrorDocument errorDocument) {
		ErrorDetail errorDetail = new ErrorDetail();

		errorDetail.setId(errorDocument.getId());

		Application application = applicationService.getApplication(errorDocument.getApplicationKey());
		
		if(application != null){
			errorDetail.setApplicationName(application.getName());
		}else{
			errorDetail.setApplicationName("Unknown application");
		}
		
		errorDetail.setMessage(errorDocument.getExceptionDetails().get(0)
				.getMessage());

		errorDetail.setTargetExceptionClassName(errorDocument.getExceptionDetails().get(0).getClassName());
		
		errorDetail.setTime(errorDocument.getTime().toString());
		return errorDetail;
	}

}
