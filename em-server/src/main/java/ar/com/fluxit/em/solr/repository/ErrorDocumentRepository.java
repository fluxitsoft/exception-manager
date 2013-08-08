package ar.com.fluxit.em.solr.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import ar.com.fluxit.em.solr.document.ErrorDocument;

public interface ErrorDocumentRepository extends
		SolrCrudRepository<ErrorDocument, String> {

	
	@Query("exceptionClassName:*?0* OR exceptionMessage:?*0*")
	public List<ErrorDocument> findByTerm(String term, Sort sort);

}