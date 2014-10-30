package ca.isda.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

import ca.isda.domain.Staff;

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
public class StaffServiceTest {
	
	private static final Logger logger = Logger.getLogger(StaffServiceTest.class);
	
	@Autowired
	private StaffService staffService;
	
	@Test
	public void testFindAll(){
		
		logger.debug("testing StaffService->findAll()");
		
		List<Staff> staffs = staffService.findAll();
		assertNotNull(staffs);
		//assertTrue(categories.size() == 4);
		
		for (Staff staff: staffs) {
			System.out.println(staff);
		}
	}
	
	
	@Test
	public void testSave(){
		logger.debug("testing StaffService->save()");
		
		Staff judy = new Staff();
		judy.setFirstName("Judy");
		judy.setLastName("Xie");
		judy.setEmail("xxx@abc.com");
		judy.setPhone("519-482-3344");
		judy.setTitle("A title");
		Staff savedStaff = staffService.save(judy);
		assertNotNull(savedStaff);
		
		List<Staff> staffs = staffService.findAll();
		assertTrue(staffs.size() == 4);
		
		logger.debug(String.format("Saved staff ==> %s", savedStaff.toString()));
		logger.info(String.format("Saved staff ==> %s", savedStaff.toString()));
	}
}
