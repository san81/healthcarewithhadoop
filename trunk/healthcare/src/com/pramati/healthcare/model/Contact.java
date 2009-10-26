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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contacts == null) ? 0 : contacts.hashCode());
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
		Contact other = (Contact) obj;
		if (contacts == null) {
			if (other.contacts != null)
				return false;
		} else if (!contacts.equals(other.contacts))
			return false;
		return true;
	}
	
}
