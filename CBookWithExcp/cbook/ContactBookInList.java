/**
 * Class ContactBookInList.
 * Margarida Lopes, n64557
 */

package cbook;

import exceptions.ContactAlreadyExistsException;
import exceptions.ContactDoesNotExistsException;
import exceptions.NoContactsException;

import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class ContactBookInList implements ContactBook {
	/**
	 * Coleccao de contactos.
	 */
	private List<Contact> contacts;
	
	/**
	 * Construtor por omissao
	 */
	public ContactBookInList() {
		contacts = new LinkedList<Contact>();
	}

	@Override 
	public boolean hasContact(String name) {
		return this.getContact(name) != null;
	}

	@Override 
	public int getNumberOfContacts() throws NoContactsException {
		if (contacts.size() == 0) {
			throw new NoContactsException();
		} else {
			return contacts.size();
		}
	}

	@Override 
	public void addContact(String name, int phone, String email) throws ContactAlreadyExistsException {
		if (hasContact(name)) {
			throw  new ContactAlreadyExistsException();
		} else {
			contacts.add(new ContactClass(name, phone, email));
		}
	}

	@Override 
	public void deleteContact(String name) throws ContactDoesNotExistsException {
		if (!hasContact(name)) {
			throw new ContactDoesNotExistsException();
		} else {
			contacts.remove(new ContactClass(name));
		}
	}

	@Override 
	public int getPhone(String name) throws ContactDoesNotExistsException {
		if (!hasContact(name)) {
			throw new ContactDoesNotExistsException();
		} else {
			return this.getContact(name).getPhone();
		}
	}

	@Override 
	public String getEmail(String name) throws ContactDoesNotExistsException{
		if (!hasContact(name)) {
			throw new ContactDoesNotExistsException();
		} else {
			return this.getContact(name).getEmail();
		}
	}

	@Override 
	public void setPhone(String name, int phone) throws ContactDoesNotExistsException {
		if (!hasContact(name)) {
			throw new ContactDoesNotExistsException();
		} else {
			this.getContact(name).setPhone(phone);
		}
	}

	@Override 
	public void setEmail(String name, String email) throws ContactDoesNotExistsException {
		if (!hasContact(name)) {
			throw new ContactDoesNotExistsException();
		} else {
			this.getContact(name).setEmail(email);
		}
	}

	@Override 
	public Iterator<Contact> listContacts() throws NoContactsException{
		if (getNumberOfContacts() == 0) {
			throw new NoContactsException();
		} else {
			return contacts.iterator();
		}
	}

	/**
	 * @param name nome do contacto a procurar na lista
	 * @return o contacto com nome <code>name</code> caso exista,
	 * 			<code>null</code> caso contrario
	 */
	private Contact getContact(String name) {
		for (Contact c: contacts)
			if (c.getName().equals(name))
				return c;
		return null;
	}
}
