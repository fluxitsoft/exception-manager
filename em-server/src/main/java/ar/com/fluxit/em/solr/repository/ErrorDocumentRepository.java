package ar.com.fluxit.em.solr.repository;

import java.util.List;

import org.springframework.data.solr.repository.SolrCrudRepository;

import ar.com.fluxit.em.solr.document.ErrorDocument;

public interface ErrorDocumentRepository extends
		SolrCrudRepository<ErrorDocument, String> {

	public List<ErrorDocument> search(String term);

}