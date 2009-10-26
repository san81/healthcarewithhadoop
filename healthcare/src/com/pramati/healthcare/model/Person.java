package com.pramati.healthcare.model;

public class Person {
	private Demography demography;

	public Demography getDemography() {
		return demography;
	}

	public void setDemography(Demography demography) {
		this.demography = demography;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((demography == null) ? 0 : demography.hashCode());
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
		Person other = (Person) obj;
		if (demography == null) {
			if (other.demography != null)
				return false;
		} else if (!demography.equals(other.demography))
			return false;
		return true;
	}

}
