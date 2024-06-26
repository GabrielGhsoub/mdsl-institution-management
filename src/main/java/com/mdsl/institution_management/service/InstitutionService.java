package com.mdsl.institution_management.service;

import com.mdsl.institution_management.mapper.InstitutionMapper;
import com.mdsl.institution_management.model.Institution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing institutions. Provides methods to perform CRUD
 * operations on institutions.
 */
@Service
public class InstitutionService {

	private final InstitutionMapper institutionMapper;

	@Autowired
	public InstitutionService(InstitutionMapper institutionMapper) {
		this.institutionMapper = institutionMapper;
	}

	/**
	 * Retrieves all institutions.
	 *
	 * @return a list of all institutions.
	 */
	public List<Institution> getAllInstitutions() {
		return institutionMapper.getAllInstitutions();
	}

	/**
	 * Retrieves an institution by its ID.
	 *
	 * @param id the ID of the institution.
	 * @return the institution with the specified ID.
	 */
	public Institution getInstitutionById(long id) {
		return institutionMapper.getInstitutionById(id);
	}

	/**
	 * Retrieves institutions by their status.
	 *
	 * @param status the status of the institutions (0 for disabled, 1 for enabled).
	 * @return a list of institutions with the specified status.
	 */
	public List<Institution> getInstitutionsByStatus(int status) {
		return institutionMapper.getInstitutionsByStatus(status);
	}

	/**
	 * Creates or updates an institution.
	 *
	 * @param institution the institution to create or update.
	 */
	public void saveInstitution(Institution institution) {
		if (institution.getInstitutionId() == null) {
			institutionMapper.createInstitution(institution);
		} else {
			institutionMapper.updateInstitution(institution);
		}
	}

	/**
	 * Deletes an institution by its ID.
	 *
	 * @param id the ID of the institution to delete.
	 */
	public void deleteInstitution(long id) {
		institutionMapper.deleteInstitution(id);
	}
}
