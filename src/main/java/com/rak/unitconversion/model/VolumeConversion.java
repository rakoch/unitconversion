package com.rak.unitconversion.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "VOLUME_CONVERSION", uniqueConstraints={
	    @UniqueConstraint(columnNames = {"unitin", "unitout"})
	})
public class VolumeConversion extends AbstractAuditedEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -33104902161724604L;

	@Id  
    @GeneratedValue(strategy=GenerationType.AUTO) 
	private Long id;
	
	@ManyToOne(cascade=CascadeType.ALL) 
	@JoinColumn(name = "value", nullable = false)
	private VolumeUnit unitIn;
	
	@ManyToOne(cascade=CascadeType.ALL) 
	@JoinColumn(name = "value", nullable = false)
	private VolumeUnit unitOut;
	
	private String formula;

	public VolumeConversion() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public VolumeUnit getUnitIn() {
		return unitIn;
	}

	public void setUnitIn(VolumeUnit unitIn) {
		this.unitIn = unitIn;
	}

	public VolumeUnit getUnitOut() {
		return unitOut;
	}

	public void setUnitOut(VolumeUnit unitOut) {
		this.unitOut = unitOut;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
	

	
}
