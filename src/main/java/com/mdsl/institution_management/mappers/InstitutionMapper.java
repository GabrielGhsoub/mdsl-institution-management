package com.mdsl.institution_management.mappers;

import org.apache.ibatis.annotations.*;
import com.mdsl.institution_management.models.Institution;
import java.util.List;

/**
 * InstitutionMapper is an interface for MyBatis that provides methods to
 * interact with the INSTITUTION table. It includes methods to retrieve, create,
 * update, and delete institutions, leveraging MyBatis annotations for mapping
 * SQL results to Java objects.
 */
@Mapper
public interface InstitutionMapper {

	/**
	 * Retrieves all institutions from the INSTITUTION table.
	 * 
	 * @return a list of all institutions.
	 */
	@Select("SELECT * FROM INSTITUTION")
	@Results({ @Result(property = "institutionId", column = "institution_id"),
			@Result(property = "institutionCode", column = "institution_code"),
			@Result(property = "institutionName", column = "institution_name"),
			@Result(property = "institutionStatus", column = "institution_status") })
	List<Institution> getAllInstitutions();

	/**
	 * Retrieves an institution by its ID.
	 * 
	 * @param id the ID of the institution to retrieve.
	 * @return the institution with the specified ID.
	 */
	@Select("SELECT * FROM INSTITUTION WHERE institution_id = #{id}")
	@Results({ @Result(property = "institutionId", column = "institution_id"),
			@Result(property = "institutionCode", column = "institution_code"),
			@Result(property = "institutionName", column = "institution_name"),
			@Result(property = "institutionStatus", column = "institution_status") })
	Institution getInstitutionById(@Param("id") long id);

	/**
	 * Retrieves institutions by their status.
	 * 
	 * @param status the status of the institutions to retrieve (0 for disabled, 1
	 *               for enabled).
	 * @return a list of institutions with the specified status.
	 */
	@Select("SELECT * FROM INSTITUTION WHERE institution_status = #{status}")
	@Results({ @Result(property = "institutionId", column = "institution_id"),
			@Result(property = "institutionCode", column = "institution_code"),
			@Result(property = "institutionName", column = "institution_name"),
			@Result(property = "institutionStatus", column = "institution_status") })
	List<Institution> getInstitutionsByStatus(@Param("status") int status);

	/**
	 * Creates a new institution in the INSTITUTION table.
	 * 
	 * @param institution the institution object to be created.
	 */
	@Insert("INSERT INTO INSTITUTION (institution_code, institution_name, institution_status) VALUES (#{institutionCode}, #{institutionName}, #{institutionStatus})")
	@Options(useGeneratedKeys = true, keyProperty = "institutionId")
	void createInstitution(Institution institution);

	/**
	 * Updates an existing institution in the INSTITUTION table.
	 * 
	 * @param institution the institution object with updated data.
	 */
	@Update("UPDATE INSTITUTION SET institution_code = #{institutionCode}, institution_name = #{institutionName}, institution_status = #{institutionStatus} WHERE institution_id = #{institutionId}")
	void updateInstitution(Institution institution);

	/**
	 * Deletes an institution from the INSTITUTION table by its ID.
	 * 
	 * @param id the ID of the institution to be deleted.
	 */
	@Delete("DELETE FROM INSTITUTION WHERE institution_id = #{id}")
	void deleteInstitution(@Param("id") long id);
}
