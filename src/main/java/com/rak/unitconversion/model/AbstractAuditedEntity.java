package com.rak.unitconversion.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractAuditedEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8244472840602247983L;

	@CreationTimestamp
	@Column(name = "CREATEDTS")
	private Date created;

	@UpdateTimestamp
	@Version // use timestamp for optimistic locking versions
	@Type(type = "dbtimestamp") // use database time instead of vm time
	@Column(name = "UPDATEDTS")
	private Date lastUpdated;

	// TODO need to introduce authentication & principal for this to work 
//	//@Column(name = "CREATEDBY")
//	@ManyToOne
//	@JoinColumn(name = "created_user_id", nullable = false)
//	private User createdBy;
//
//	//@Column(name = "LASTUPDBY")
//	@ManyToOne
//	@JoinColumn(name = "updated_user_id", nullable = false)
//	private User lastUpdatedBy;


	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

//	public User getCreatedBy() {
//		return createdBy;
//	}
//
//	public void setCreatedBy(User createdBy) {
//		this.createdBy = createdBy;
//	}
//
//	public User getLastUpdatedBy() {
//		return lastUpdatedBy;
//	}
//
//	public void setLastUpdatedBy(User lastUpdatedBy) {
//		this.lastUpdatedBy = lastUpdatedBy;
//	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
//		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((lastUpdated == null) ? 0 : lastUpdated.hashCode());
//		result = prime * result + ((lastUpdatedBy == null) ? 0 : lastUpdatedBy.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractAuditedEntity other = (AbstractAuditedEntity) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
//		if (createdBy == null) {
//			if (other.createdBy != null)
//				return false;
//		} else if (!createdBy.equals(other.createdBy))
//			return false;
		if (lastUpdated == null) {
			if (other.lastUpdated != null)
				return false;
		} else if (!lastUpdated.equals(other.lastUpdated))
			return false;
//		if (lastUpdatedBy == null) {
//			if (other.lastUpdatedBy != null)
//				return false;
//		} else if (!lastUpdatedBy.equals(other.lastUpdatedBy))
//			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractAuditedEntity [created=" + created + ", lastUpdated=" + lastUpdated + 
				//", createdBy=" + createdBy + ", lastUpdatedBy=" + lastUpdatedBy + 
				"]";
	}

}