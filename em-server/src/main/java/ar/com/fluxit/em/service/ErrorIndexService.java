package ar.com.fluxit.em.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ar.com.fluxit.em.solr.document.ErrorDocument;
import ar.com.fluxit.em.solr.repository.ErrorDocumentRepository;

@Service
public class ErrorIndexService {

//	@Autowired
	private ErrorDocumentRepository repository;

	public void add(ar.com.fluxit.em.model.ErrorDocument error) {
//		ErrorDocument document = new ErrorDocument(error);
//		repository.save(document);

	}

	public void delete(String id) {
		repository.delete(id);

	}

	public List<ErrorDocument> find(String searchTerm) {
		return repository.search(searchTerm);

	}
}
