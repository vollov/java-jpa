package ca.isda.service;

import java.util.List;

import ca.isda.domain.Staff;

public interface StaffService {
	public List<Staff> findAll();

	public Staff save(Staff staff);

	public Staff findById(long id);
}
