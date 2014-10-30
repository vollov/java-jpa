package ca.isda.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import ca.isda.domain.Staff;

@Service("staffService")
@Repository
@Transactional
public class StaffServiceImpl implements StaffService {
	private static final Logger logger = Logger.getLogger(StaffServiceImpl.class);
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public List<Staff> findAll() {
		TypedQuery<Staff> query = entityManager.createQuery("SELECT s FROM Staff s", Staff.class);
		List<Staff> staffs = query.getResultList();
		return staffs;
	}

	@Override
	public Staff save(Staff staff) {
		if (staff.getId() != null) {
			logger.info("Inserting new staff");
            this.entityManager.merge(staff);
        } else {
        	logger.info("Updating existing staff");
            this.entityManager.persist(staff);
        }
		
		logger.info("Staff saved with id: " + staff.getId());
		return staff;
	}

	@Override
	public Staff findById(long id) {
		return this.entityManager.find(Staff.class, id);
	}

}
