package com.github.davidherdu.code_challenge.beans;

import java.io.Serializable;

public class Parcel implements Serializable {

	private static final long serialVersionUID = 7222508282053296359L;

	private int weight;
	private int width;
	private int height;
	private int lenght;
	
	public Parcel() {
		super();
	}

	public Parcel(int weight, int width, int height, int lenght) {
		super();
		this.weight = weight;
		this.width = width;
		this.height = height;
		this.lenght = lenght;
	}

	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getLenght() {
		return lenght;
	}
	
	public void setLenght(int lenght) {
		this.lenght = lenght;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + height;
		result = prime * result + lenght;
		result = prime * result + weight;
		result = prime * result + width;
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
		Parcel other = (Parcel) obj;
		if (height != other.height)
			return false;
		if (lenght != other.lenght)
			return false;
		if (weight != other.weight)
			return false;
		if (width != other.width)
			return false;
		return true;
	}
}
