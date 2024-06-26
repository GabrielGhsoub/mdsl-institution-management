package com.mdsl.institution_management.service;

import com.mdsl.institution_management.mapper.InstitutionMapper;
import com.mdsl.institution_management.model.Institution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class InstitutionServiceTest {

	@InjectMocks
	private InstitutionService institutionService;

	@Mock
	private InstitutionMapper institutionMapper;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllInstitutions() {
		Institution institution1 = new Institution();
		institution1.setInstitutionId(1L);
		Institution institution2 = new Institution();
		institution2.setInstitutionId(2L);

		when(institutionMapper.getAllInstitutions()).thenReturn(Arrays.asList(institution1, institution2));

		List<Institution> institutions = institutionService.getAllInstitutions();

		assertEquals(2, institutions.size());
		assertEquals(1L, institutions.get(0).getInstitutionId());
		assertEquals(2L, institutions.get(1).getInstitutionId());
	}

	@Test
	public void testGetInstitutionById() {
		Institution institution = new Institution();
		institution.setInstitutionId(1L);

		when(institutionMapper.getInstitutionById(anyLong())).thenReturn(institution);

		Institution foundInstitution = institutionService.getInstitutionById(1L);

		assertNotNull(foundInstitution);
		assertEquals(1L, foundInstitution.getInstitutionId());
	}

	@Test
	public void testGetInstitutionsByStatus() {
		Institution institution1 = new Institution();
		institution1.setInstitutionId(1L);
		Institution institution2 = new Institution();
		institution2.setInstitutionId(2L);

		when(institutionMapper.getInstitutionsByStatus(anyInt())).thenReturn(Arrays.asList(institution1, institution2));

		List<Institution> institutions = institutionService.getInstitutionsByStatus(1);

		assertEquals(2, institutions.size());
		assertEquals(1L, institutions.get(0).getInstitutionId());
		assertEquals(2L, institutions.get(1).getInstitutionId());
	}

	@Test
	public void testSaveInstitution_Create() {
		Institution institution = new Institution();
		institution.setInstitutionCode("12345");
		institution.setInstitutionName("Test Institution");
		institution.setInstitutionStatus(1);

		doNothing().when(institutionMapper).createInstitution(any(Institution.class));

		institutionService.saveInstitution(institution);

		verify(institutionMapper, times(1)).createInstitution(institution);
		verify(institutionMapper, never()).updateInstitution(institution);
	}

	@Test
	public void testSaveInstitution_Update() {
		Institution institution = new Institution();
		institution.setInstitutionId(1L);
		institution.setInstitutionCode("12345");
		institution.setInstitutionName("Updated Institution");
		institution.setInstitutionStatus(1);

		doNothing().when(institutionMapper).updateInstitution(any(Institution.class));

		institutionService.saveInstitution(institution);

		verify(institutionMapper, never()).createInstitution(institution);
		verify(institutionMapper, times(1)).updateInstitution(institution);
	}

	@Test
	public void testDeleteInstitution() {
		doNothing().when(institutionMapper).deleteInstitution(anyLong());

		institutionService.deleteInstitution(1L);

		verify(institutionMapper, times(1)).deleteInstitution(1L);
	}
}
