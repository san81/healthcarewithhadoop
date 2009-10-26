package com.pramati.healthcare.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a patient healthcare record.
 * 
 * @author nitin
 * 
 */
public class Patient extends Person {
	private List<Problem> problems = new ArrayList<Problem>();
	private int patientId;

	public List<Problem> getProblems() {
		return problems;
	}

	public void setProblem(Problem aProblem) {
		this.problems.add(aProblem);
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + patientId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		if (patientId != other.patientId)
			return false;
		return true;
	}

}
