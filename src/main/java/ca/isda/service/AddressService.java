package ca.isda.service;

import java.util.List;

import ca.isda.domain.Address;

public interface AddressService {
	public List<Address> findAll();

	public Address save(Address address);

	public Address findById(long id);
}
