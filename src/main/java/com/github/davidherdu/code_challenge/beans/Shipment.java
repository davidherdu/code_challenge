package com.github.davidherdu.code_challenge.beans;

import java.io.Serializable;
import java.util.List;

public class Shipment implements Serializable {
	
	private static final long serialVersionUID = 5024313961598316473L;

	private String reference;
	private List<Parcel> parcels;
		
	public Shipment() {
		super();
	}

	public Shipment(String reference, List<Parcel> parcels) {
		super();
		this.reference = reference;
		this.parcels = parcels;
	}

	public String getReference() {
		return reference;
	}
	
	public void setReference(String reference) {
		this.reference = reference;
	}
	
	public List<Parcel> getParcels() {
		return parcels;
	}
	
	public void setParcels(List<Parcel> parcels) {
		this.parcels = parcels;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parcels == null) ? 0 : parcels.hashCode());
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
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
		Shipment other = (Shipment) obj;
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
		return true;
	}
}
