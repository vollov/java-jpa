package ca.isda.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import ca.isda.domain.Address;
import ca.isda.domain.Address;
import ca.isda.domain.Address;

@Service("addressService")
@Repository
@Transactional
public class AddressServiceImpl implements AddressService {
	private static final Logger logger = Logger.getLogger(AddressServiceImpl.class);
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Address> findAll() {
		TypedQuery<Address> query = entityManager.createQuery("SELECT c FROM Address c", Address.class);
		List<Address> addresses = query.getResultList();
		logger.info("Address listing " + addresses.size());
		return addresses;
	}

	@Override
	public Address save(Address address) {
		if (address.getId() != null) {
			logger.info("Inserting new address");
            entityManager.merge(address);
        } else {
        	logger.info("Updating existing address");
            entityManager.persist(address);
        }
		
		logger.info("Address saved with id: " + address.getId());
		return address;
	}

	@Override
	public Address findById(long id) {
		return entityManager.find(Address.class, id);
	}

}
