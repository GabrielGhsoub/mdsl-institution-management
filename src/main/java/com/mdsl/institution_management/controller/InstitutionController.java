package com.mdsl.institution_management.controller;

import com.mdsl.institution_management.model.Institution;
import com.mdsl.institution_management.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing institutions.
 */
@RestController
@RequestMapping("/api/institutions")
public class InstitutionController {

	private final InstitutionService institutionService;

	@Autowired
	public InstitutionController(InstitutionService institutionService) {
		this.institutionService = institutionService;
	}

	/**
	 * GET /api/institutions : get all institutions.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of institutions
	 *         in body.
	 */
	@GetMapping
	public ResponseEntity<List<Institution>> getAllInstitutions() {
		List<Institution> institutions = institutionService.getAllInstitutions();
		return ResponseEntity.ok(institutions);
	}

	/**
	 * GET /api/institutions/{id} : get the "id" institution.
	 *
	 * @param id the id of the institution to retrieve.
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         institution, or with status 404 (Not Found).
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> getInstitutionById(@PathVariable Long id) {
		Institution institution = institutionService.getInstitutionById(id);
		if (institution != null) {
			return ResponseEntity.ok(institution);
		} else {
			return ResponseEntity.status(404).body("Institution with ID " + id + " not found.");
		}
	}

	/**
	 * GET /api/institutions/status/{status} : get institutions by status.
	 *
	 * @param status the status of the institutions to retrieve.
	 * @return the ResponseEntity with status 200 (OK) and the list of institutions
	 *         with the specified status.
	 */
	@GetMapping("/status/{status}")
	public ResponseEntity<List<Institution>> getInstitutionsByStatus(@PathVariable int status) {
		List<Institution> institutions = institutionService.getInstitutionsByStatus(status);
		return ResponseEntity.ok(institutions);
	}

	/**
	 * POST /api/institutions : create a new institution.
	 *
	 * @param institution the institution to create.
	 * @return the ResponseEntity with status 201 (Created) and with message.
	 */
	@PostMapping
	public ResponseEntity<String> createInstitution(@RequestBody Institution institution) {
		institutionService.saveInstitution(institution);
		return ResponseEntity.status(201)
				.body("Institution created successfully with ID: " + institution.getInstitutionId());
	}

	/**
	 * PUT /api/institutions : update an existing institution.
	 *
	 * @param institution the institution to update.
	 * @return the ResponseEntity with status 200 (OK) and with message.
	 */
	@PutMapping
	public ResponseEntity<String> updateInstitution(@RequestBody Institution institution) {
		institutionService.saveInstitution(institution);
		return ResponseEntity.ok("Institution updated successfully with ID: " + institution.getInstitutionId());
	}

	/**
	 * DELETE /api/institutions/{id} : delete the "id" institution.
	 *
	 * @param id the id of the institution to delete.
	 * @return the ResponseEntity with status 204 (No Content) and with message.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteInstitution(@PathVariable Long id) {
		institutionService.deleteInstitution(id);
		return ResponseEntity.status(204).body("Institution with ID " + id + " deleted successfully.");
	}
}
