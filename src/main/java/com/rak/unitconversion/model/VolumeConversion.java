package com.rak.unitconversion.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "VOLUME_CONVERSION")
//, uniqueConstraints={
//	    @UniqueConstraint(columnNames = {"unitin", "unitout"})
//	})
public class VolumeConversion extends AbstractAuditedEntity implements UnitConversionModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -33104902161724604L;

	@Id  
    @GeneratedValue(strategy=GenerationType.AUTO) 
	private Long id;
	
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(referencedColumnName = "value", nullable = false)
	private VolumeUnit unitin;
	
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(referencedColumnName = "value", nullable = false)
	private VolumeUnit unitout;
	
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


	public VolumeUnit getUnitin() {
		return unitin;
	}

	public void setUnitin(VolumeUnit unitin) {
		this.unitin = unitin;
	}

	public VolumeUnit getUnitout() {
		return unitout;
	}

	public void setUnitout(VolumeUnit unitout) {
		this.unitout = unitout;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
	

	
}
