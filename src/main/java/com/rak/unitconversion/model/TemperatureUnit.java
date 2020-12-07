package com.rak.unitconversion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TEMERATURE_UNIT")
public class TemperatureUnit extends AbstractAuditedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -160808674886129608L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name="value", unique = true)
	private TemperatureUnitEnum temperatureUnitEnum;

	public TemperatureUnit() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public TemperatureUnitEnum getTemperatureUnitEnum() {
		return temperatureUnitEnum;
	}


	public void setTemperatureUnitEnum(TemperatureUnitEnum temperatureUnitEnum) {
		this.temperatureUnitEnum = temperatureUnitEnum;
	}


}
