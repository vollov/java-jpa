package ca.isda.service;

import java.util.List;

//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;

import ca.isda.domain.Content;

public interface ContentService {
	public List<Content> findAll();
	
	public Content findByKey(String code, String locale);
	
	public Content save(Content content);
	
	//public Page<Content> findAllByPage(Pageable pageable);
}
