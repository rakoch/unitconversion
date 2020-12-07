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
@Table(name = "TEMPERATURE_CONVERSION", uniqueConstraints={
	    @UniqueConstraint(columnNames = {"unitin", "unitout"})
	})
public class TemperatureConversion extends AbstractAuditedEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4988822827745162902L;

	@Id  
    @GeneratedValue(strategy=GenerationType.AUTO) 
	private Long id;
	
	@ManyToOne(cascade=CascadeType.ALL) 
	@JoinColumn(name = "value", nullable = false)
	private TemperatureUnit unitIn;
	
	@ManyToOne(cascade=CascadeType.ALL) 
	@JoinColumn(name = "value", nullable = false)
	private TemperatureUnit unitOut;
	
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

	public TemperatureUnit getUnitIn() {
		return unitIn;
	}

	public void setUnitIn(TemperatureUnit unitIn) {
		this.unitIn = unitIn;
	}

	public TemperatureUnit getUnitOut() {
		return unitOut;
	}

	public void setUnitOut(TemperatureUnit unitOut) {
		this.unitOut = unitOut;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
	

	
}
