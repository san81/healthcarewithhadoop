package com.pramati.healthcare.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Create a patient healthcare record.
 * 
 * @author nitin
 * 
 */
public class Patient extends Person {
	private List<Problem> problems;

	public Patient(){
		problems = new ArrayList<Problem>();
	}
	public List<Problem> getProblems() {
		return problems;
	}

	public void setProblem(Problem problems) {
		this.problems.add(problems);
	}
}
