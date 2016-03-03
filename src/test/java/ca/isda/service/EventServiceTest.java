package ca.isda.service;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import ca.isda.domain.Address;
import ca.isda.domain.Event;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {PersistenceContext.class})
@ContextConfiguration("classpath:test-jpa-context.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
      DirtiesContextTestExecutionListener.class,
      TransactionalTestExecutionListener.class,
      DbUnitTestExecutionListener.class })
@DatabaseSetup("dataset.xml")
public class EventServiceTest {
	private static final Logger logger = Logger.getLogger(EventServiceTest.class);
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private AddressService addressService;
	
	@Test
	public void testFind(){
		
		logger.debug("testing EventService->find()");
		
		Event event = eventService.findById(1);
		System.out.println(event);
	}
	
	@Test
	public void testSave(){
		logger.debug("testing EventService->save()");
		Address a2 = addressService.findById(2);
		Event event = new Event();
		event.setActive(true);
		event.setAddress(a2);
		event.setDescription("Boating club");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date eventTime;
		try {
			eventTime = formatter.parse("2013-01-13T17:30:00");
			event.setEventTime(eventTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		eventService.save(event);
		
		List<Event> events = eventService.findAll();
		assertTrue(events.size() == 2);

		logger.info(String.format("Saved event ==> %s", event.toString()));
	}
}
