package com.rak.unitconversion.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Fetch;

@Entity
@Table(name = "TEMPERATURE_CONVERSION")
//, uniqueConstraints={
//	    @UniqueConstraint(columnNames = {"unitin", "unitout"})
//	})
public class TemperatureConversion extends AbstractAuditedEntity implements UnitConversionModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4988822827745162902L;

	@Id  
    @GeneratedValue(strategy=GenerationType.AUTO) 
	private Long id;
	
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(referencedColumnName = "value", nullable = false)
	private TemperatureUnit unitin;
	
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(referencedColumnName = "value", nullable = false)
	private TemperatureUnit unitout;
	
	private String formula;

	public TemperatureConversion() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	public TemperatureUnit getUnitin() {
		return unitin;
	}

	public void setUnitin(TemperatureUnit unitin) {
		this.unitin = unitin;
	}

	public TemperatureUnit getUnitout() {
		return unitout;
	}

	public void setUnitout(TemperatureUnit unitout) {
		this.unitout = unitout;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
	

	
}
