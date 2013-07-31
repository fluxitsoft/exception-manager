package ar.com.fluxit.em.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import ar.com.fluxit.em.model.Application;

@Service
public class ApplicationService {

	@Autowired
	ExceptionDescriptorRepository exceptionDescriptorRepository;

	@Autowired
	MongoOperations mongoOperations;

	public Application add(Application application) {

		if (application.getKey() == null) {
			application.setKey(UUID.randomUUID().toString());
		}

		mongoOperations.insert(application, "applications");

		return application;

	}

	public List<Application> getApplications() {
		return mongoOperations.findAll(Application.class, "applications");
	}

	public Application getApplication(String applicationKey) {
		Query q = new Query().addCriteria(new Criteria().where("key").is(applicationKey));
		Application application = mongoOperations.findOne(q, Application.class, "applications");
		return application;
	}


}
