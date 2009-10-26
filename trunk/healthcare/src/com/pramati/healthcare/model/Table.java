package com.pramati.healthcare.model;

import java.util.Set;

public class Table {
	private Set<String> coloumnFamilies;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getColoumnFamilies() {
		return coloumnFamilies;
	}

	public void setColoumnFamilies(Set<String> coloumnFamilies) {
		this.coloumnFamilies = coloumnFamilies;
	}

	@Override
	public String toString() {
		return "Table [coloumnFamilies=" + coloumnFamilies + ", name=" + name
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((coloumnFamilies == null) ? 0 : coloumnFamilies.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Table other = (Table) obj;
		if (coloumnFamilies == null) {
			if (other.coloumnFamilies != null)
				return false;
		} else if (!coloumnFamilies.equals(other.coloumnFamilies))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
