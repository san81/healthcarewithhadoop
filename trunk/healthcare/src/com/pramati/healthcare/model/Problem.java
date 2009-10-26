package com.pramati.healthcare.model;

import java.util.Date;

public class Problem implements Comparable<Problem> {
	private String description;
	private Date startDate;
	private Episodicity episodicity;
	private Severity severity;
	private Course course;
	private Onset onset;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Episodicity getEpisodicity() {
		return episodicity;
	}

	public void setEpisodicity(Episodicity episodicity) {
		this.episodicity = episodicity;
	}

	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Onset getOnset() {
		return onset;
	}

	public void setOnset(Onset onset) {
		this.onset = onset;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Problem other = (Problem) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (episodicity == null) {
			if (other.episodicity != null)
				return false;
		} else if (!episodicity.equals(other.episodicity))
			return false;
		if (onset == null) {
			if (other.onset != null)
				return false;
		} else if (!onset.equals(other.onset))
			return false;
		if (severity == null) {
			if (other.severity != null)
				return false;
		} else if (!severity.equals(other.severity))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((episodicity == null) ? 0 : episodicity.hashCode());
		result = prime * result + ((onset == null) ? 0 : onset.hashCode());
		result = prime * result
				+ ((severity == null) ? 0 : severity.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	/**
	 * sorts in reverse order by start date, that is, latest first.
	 */
	@Override
	public int compareTo(Problem other) {
		return -1 * (this.getStartDate().compareTo(other.getStartDate()));
	}

	@Override
	public String toString() {
		return "Problem [description=" + description + ", startDate="
				+ startDate + "]";
	}

}
