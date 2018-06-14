package com.github.davidherdu.code_challenge.beans;

import java.io.Serializable;

public class Tracking implements Serializable {

	private static final long serialVersionUID = 862996167080202451L;

	private Status status;
	private Integer parcels;
	private Integer weight;
	private String reference;
	
	public Tracking() {
		super();
	}

	public Tracking(Status status, Integer parcels, Integer weight, String reference) {
		super();
		this.status = status;
		this.parcels = parcels;
		this.weight = weight;
		this.reference = reference;
	}

	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Integer getParcels() {
		return parcels;
	}
	
	public void setParcels(Integer parcels) {
		this.parcels = parcels;
	}
	
	public Integer getWeight() {
		return weight;
	}
	
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	public String getReference() {
		return reference;
	}
	
	public void setReference(String reference) {
		this.reference = reference;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parcels == null) ? 0 : parcels.hashCode());
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
		Tracking other = (Tracking) obj;
		if (parcels == null) {
			if (other.parcels != null)
				return false;
		} else if (!parcels.equals(other.parcels))
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		if (status != other.status)
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}
}
