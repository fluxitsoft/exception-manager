package ar.com.fluxit.em.solr.repository;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.repository.support.SimpleSolrRepository;

import ar.com.fluxit.em.solr.document.ErrorDocument;

public class ErrorDocumentRepositoryImpl extends
		SimpleSolrRepository<ErrorDocument> implements ErrorDocumentRepository {

	@Resource
	private SolrTemplate solrTemplate;

	public List<ErrorDocument> search(String term) {

//        String[] words = term.split(" ");
//        Criteria conditions = createSearchConditions(words);
//        SimpleQuery query = new SimpleQuery(conditions);
//
//        return solrTemplate.queryForPage(query, ErrorDocument.class).getContent();
		return null;
    }

//    private Criteria createSearchConditions(String[] words) {
//        Criteria conditions = null;
//
//        for (String word: words) {
//            if (conditions == null) {
//                conditions = new Criteria("").contains(word)
//                        .or(new Criteria(TodoDocument.FIELD_DESCRIPTION).contains(word));
//            }
//            else {
//                conditions = conditions.or(new Criteria(TodoDocument.FIELD_TITLE).contains(word))
//                        .or(new Criteria(TodoDocument.FIELD_DESCRIPTION).contains(word));
//            }
//        }
//
//        return conditions;
//    }
}