package com.mdsl.institution_management.model;

import lombok.Data;

/**
 * Institution is a model class representing the INSTITUTION table. It uses
 * Lombok annotations to generate boilerplate code such as getters, setters, and
 * toString method.
 */
@Data
public class Institution {
	private Long institutionId;
	private String institutionCode;
	private String institutionName;
	private Integer institutionStatus;
}
