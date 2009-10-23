package com.pramati.healthcare.model;

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
	private DateOfBirth dob;
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

	public DateOfBirth getDob() {
		return dob;
	}

	public void setDob(DateOfBirth dob) {
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
