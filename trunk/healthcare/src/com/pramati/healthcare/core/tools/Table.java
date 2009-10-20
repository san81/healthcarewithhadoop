package com.pramati.healthcare.core.tools;

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
		return name;
	}
}
