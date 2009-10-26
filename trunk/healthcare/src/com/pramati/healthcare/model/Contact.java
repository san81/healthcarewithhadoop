package com.pramati.healthcare.model;

import java.util.Map;

public class Contact {
	private Map<ContactType, String> contacts;

	public Map<ContactType, String> getContacts() {
		return contacts;
	}

	public void setContact(ContactType contactType, String contactValue) {
		if(contacts==null)
			contacts = new java.util.HashMap<ContactType, String>();			
		contacts.put(contactType, contactValue);
	}

	public String getContactDetailByContactType(ContactType contactType) {
		return contacts.get(contactType);
	}
}
