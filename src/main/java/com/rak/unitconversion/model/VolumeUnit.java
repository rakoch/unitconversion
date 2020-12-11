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
@Table(name = "VOLUME_UNIT")
public class VolumeUnit extends AbstractAuditedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1468736202566224507L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name="value", unique = true)
	private VolumeUnitEnum volumeUnitEnum;

	public VolumeUnit() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public VolumeUnitEnum getVolumeUnitEnum() {
		return volumeUnitEnum;
	}


	public void setVolumeUnitEnum(VolumeUnitEnum volumeUnitEnum) {
		this.volumeUnitEnum = volumeUnitEnum;
	}





}
