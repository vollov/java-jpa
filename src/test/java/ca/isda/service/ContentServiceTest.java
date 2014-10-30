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

import ca.isda.domain.Content;

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
public class ContentServiceTest {
	
	private static final Logger logger = Logger.getLogger(StaffServiceTest.class);
	
	@Autowired
	private ContentService contentService;
	
	@Test
	public void testFindByKey(){
		String code = "page.about";
		String locale = "zh";
		
		logger.debug("testing ContentService->findByKey()");
		
		Content content = contentService.findByKey(code, locale);
		assertNotNull(content);
		
	}
	
	@Test
	public void testSave(){
		logger.debug("testing ContentService->save()");
		
		Content content = new Content();
		content.setCode("page.education");
		content.setLocale("en");
		content.setContent("Some Content - en");
		
		Content savedContent = contentService.save(content);
		assertNotNull(savedContent);
		
		List<Content> contents = contentService.findAll();
		assertTrue(contents.size() == 4);
		
		//logger.debug(String.format("Saved staff ==> %s", savedStaff.toString()));
		logger.info(String.format("Saved staff ==> %s", savedContent.toString()));
	}
	
	@Test
	public void testUpdate(){
		logger.debug("testing ContentService->save()");
		
		Content content = new Content();
		content.setCode("page.education");
		content.setLocale("en");
		content.setContent("Some Content - enxxx");
		
		Content savedContent = contentService.save(content);
		assertNotNull(savedContent);
		
		List<Content> contents = contentService.findAll();
		assertTrue(contents.size() == 4);
		
		//logger.debug(String.format("Saved staff ==> %s", savedStaff.toString()));
		logger.info(String.format("Saved staff ==> %s", savedContent.toString()));
	}
}