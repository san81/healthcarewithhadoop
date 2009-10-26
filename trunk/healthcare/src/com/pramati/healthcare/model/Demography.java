package com.pramati.healthcare.model;

import java.util.Date;

/**
 * Captures statistical characteristic of a human population (such as name, age,
 * zip code or income) that is important for creating particular target-groups.
 * 
 * @author nitin
 * 
 */
public class Demography {

	private Name name;
	private Gender gender;
	private Date dob;
	private Address address;
	private Contact contact;

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}
	
	public int getAge(){
		// TODO:
		return 0;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
