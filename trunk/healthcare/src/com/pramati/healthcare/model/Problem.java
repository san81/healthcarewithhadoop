package com.pramati.healthcare.model;

import java.util.Date;

public class Problem {
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

}
