package ca.isda.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import ca.isda.domain.Content;
import ca.isda.domain.ContentPK;

@Service("contentService")
@Repository
@Transactional
public class ContentServiceImpl implements ContentService {

	private static final Logger logger = Logger.getLogger(ContentServiceImpl.class);
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Content> findAll() {
		TypedQuery<Content> query = entityManager.createQuery("SELECT c FROM Content c", Content.class);
		List<Content> contents = query.getResultList();
		return contents;
	}

	@Override
	public Content findByKey(String code, String locale) {
		ContentPK key = new ContentPK(code, locale);
		return this.entityManager.find(Content.class, key);
	}

	@Override
	public Content save(Content content) {
        Content persistedContent = this.entityManager.merge(content);
		logger.info("Staff saved with id: " + persistedContent.getCode());
		return persistedContent;
	}

}
