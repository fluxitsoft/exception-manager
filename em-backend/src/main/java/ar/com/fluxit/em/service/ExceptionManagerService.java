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
import org.springframework.transaction.annotation.Transactional;

import ar.com.fluxit.em.controller.ErrorDetail;
import ar.com.fluxit.em.model.Application;
import ar.com.fluxit.em.model.Error;
import ar.com.fluxit.em.model.ErrorDocument;
import ar.com.fluxit.em.model.ExceptionDescriptor;
import ar.com.fluxit.em.model.MemoryContext;

@Service
public class ExceptionManagerService {

	@Autowired
	ExceptionDescriptorRepository exceptionDescriptorRepository;

	@Autowired
	ApplicationService applicationService;

	@Autowired
	MongoOperations mongoOperations;

	public String addError(ar.com.fluxit.em.model.ErrorDocument error) {
		error.setTime(new Date());
		mongoOperations.insert(error, "errors");
		
		CometService.instance.publish("admin", toErrorDetail(error));
		
		return error.getId();
	}


	@Transactional
	public void updateExceptionDescriptor(String className, String description) {

		ExceptionDescriptor exceptionDescriptor = exceptionDescriptorRepository
				.getExceptionDescriptor(className);
		if (exceptionDescriptor == null) {
			exceptionDescriptor = new ExceptionDescriptor();
			exceptionDescriptor.setClassName(className);
		}

		exceptionDescriptor.setDescription(description);

		exceptionDescriptorRepository.update(exceptionDescriptor);

	}

	public int simulateException() {
		return 1 / 0;

	}

	@Transactional
	public void simulateDbException() {

		ExceptionDescriptor exceptionDescriptor = new ExceptionDescriptor();
		exceptionDescriptor.setClassName(ArithmeticException.class.getName());
		exceptionDescriptorRepository.update(exceptionDescriptor);

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
