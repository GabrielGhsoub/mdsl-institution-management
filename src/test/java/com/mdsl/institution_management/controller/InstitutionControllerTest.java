package com.mdsl.institution_management.controller;

import com.mdsl.institution_management.model.Institution;
import com.mdsl.institution_management.service.InstitutionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class InstitutionControllerTest {

	@InjectMocks
	private InstitutionController institutionController;

	@Mock
	private InstitutionService institutionService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllInstitutions() {
		List<Institution> institutions = new ArrayList<>();
		institutions.add(new Institution());

		when(institutionService.getAllInstitutions()).thenReturn(institutions);

		ResponseEntity<List<Institution>> response = institutionController.getAllInstitutions();

		assertEquals(200, response.getStatusCodeValue());
		assertEquals(1, response.getBody().size());
	}

	@Test
	public void testGetInstitutionById_Success() {
		Institution institution = new Institution();
		institution.setInstitutionId(1L);

		when(institutionService.getInstitutionById(anyLong())).thenReturn(institution);

		ResponseEntity<Object> response = institutionController.getInstitutionById(1L);

		assertEquals(200, response.getStatusCodeValue());
		assertNotNull(response.getBody());
		assertEquals(institution, response.getBody());
	}

	@Test
	public void testGetInstitutionById_NotFound() {
		when(institutionService.getInstitutionById(anyLong())).thenReturn(null);

		ResponseEntity<Object> response = institutionController.getInstitutionById(1L);

		assertEquals(404, response.getStatusCodeValue());
		assertEquals("Institution with ID 1 not found.", response.getBody());
	}

	@Test
	public void testCreateInstitution() {
		Institution institution = new Institution();
		institution.setInstitutionId(1L);

		doNothing().when(institutionService).saveInstitution(any(Institution.class));

		ResponseEntity<String> response = institutionController.createInstitution(institution);

		assertEquals(201, response.getStatusCodeValue());
		assertEquals("Institution created successfully with ID: 1", response.getBody());
	}

	@Test
	public void testUpdateInstitution() {
		Institution institution = new Institution();
		institution.setInstitutionId(1L);

		doNothing().when(institutionService).saveInstitution(any(Institution.class));

		ResponseEntity<String> response = institutionController.updateInstitution(institution);

		assertEquals(200, response.getStatusCodeValue());
		assertEquals("Institution updated successfully with ID: 1", response.getBody());
	}

	@Test
	public void testDeleteInstitution() {
		doNothing().when(institutionService).deleteInstitution(anyLong());

		ResponseEntity<String> response = institutionController.deleteInstitution(1L);

		assertEquals(204, response.getStatusCodeValue());
		assertEquals("Institution with ID 1 deleted successfully.", response.getBody());
	}
}
