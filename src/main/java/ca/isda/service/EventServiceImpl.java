package ca.isda.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import ca.isda.domain.Event;
import ca.isda.domain.Event;
import ca.isda.domain.Staff;

@Service("eventService")
@Repository
@Transactional
public class EventServiceImpl implements EventService {

	private static final Logger logger = Logger.getLogger(EventServiceImpl.class);
	
	@PersistenceContext
    private EntityManager entityManager;
	
	/**
	 * Find most recent 20 Events order by time descend
	 */
	@Override
	public List<Event> findAll() {
		TypedQuery<Event> query = entityManager.createQuery("SELECT e FROM Event e ORDER BY e.eventTime", Event.class);
		List<Event> events = query.setMaxResults(20).getResultList();
		logger.info("Event listing " + events.size());
		return events;
	}

	@Override
	public Event save(Event event) {
		if (event.getId() != null) {
			logger.info("Inserting new event");
            entityManager.merge(event);
        } else {
        	logger.info("Updating existing event");
            entityManager.persist(event);
        }
		
		logger.info("Event saved with id: " + event.getId());
		return event;
	}

	@Override
	public Event findById(long id) {
		return entityManager.find(Event.class, id);
	}
}
