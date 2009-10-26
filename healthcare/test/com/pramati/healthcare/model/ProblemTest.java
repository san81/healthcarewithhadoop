package com.pramati.healthcare.model;

import static junit.framework.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class ProblemTest {
	@Test
	public void testEquals() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Problem problem = new Problem();
		problem.setStartDate(dateFormat.parse("26/10/2009"));
		Problem anotherProblem = new Problem();
		anotherProblem.setStartDate(dateFormat.parse("26/10/2009"));
		assertEquals(false, problem.equals(null));
		assertEquals(true, problem.equals(problem));
		assertEquals(true, problem.equals(anotherProblem));
	}

	public void testCompareTo() throws ParseException {
		Problem problem = new Problem();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		problem.setStartDate(dateFormat.parse("26/10/2009"));
		Problem problem2 = new Problem();
		problem2.setStartDate(dateFormat.parse("25/10/2009"));
		Problem problem3 = new Problem();
		problem3.setStartDate(dateFormat.parse("27/10/2009"));
		List<Problem> problemList = new ArrayList<Problem>();
		problemList.add(problem);
		problemList.add(problem2);
		problemList.add(problem3);
		Collections.sort(problemList);
		assertEquals(problem3, problemList.get(0));
	}
}
