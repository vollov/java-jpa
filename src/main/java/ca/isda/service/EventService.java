package ca.isda.service;

import java.util.List;

import ca.isda.domain.Event;

public interface EventService {
	public List<Event> findAll();

	public Event save(Event event);

	public Event findById(long id);
}
