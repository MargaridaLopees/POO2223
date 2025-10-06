/**
 * Interface ContactBook.
 * Margarida Lopes, n64557
 */

package cbook;

import exceptions.ContactAlreadyExistsException;
import exceptions.ContactDoesNotExistsException;
import exceptions.NoContactsException;

import java.util.Iterator;

public interface ContactBook {

	/**
	 * Verifica se ja existe um contacto com o nome dado
	 * @param name indica o nome do contacto 
	 * @return <code>true</code> se o contacto com nome <code>name</code> existe,
	 * 			<code>false</code> caso contrario
	 */
	boolean hasContact(String name) throws ContactAlreadyExistsException;

	/**
	 * Devolve o numero de contactos
	 * @return o numero de contactos
	 * @throws NoContactsException se nao existirem contactos na agenda.
	 */
	public int getNumberOfContacts() throws NoContactsException;

	/**
	 * Adiciona um novo contacto a agenda
	 * @param name indica o nome do contacto 
	 * @param phone indica o numero de telefone do contacto
	 * @param email indica o email do contacto
	 * @throws ContactAlreadyExistsException se o contacto ja existir na agenda.
	 * @pre !hasContact(name)
	 */
	void addContact(String name, int phone, String email) throws ContactAlreadyExistsException;

	/**
	 * Remove um contacto dado o nome
	 * @param name indica o nome do contacto a remover
	 * @throws ContactDoesNotExistsException se o contacto nao existir na agenda.
	 * @pre hasContact(name)
	 */
	void deleteContact(String name) throws ContactDoesNotExistsException;

	/**
	 * Consulta o numero de telefone de um contacto dado o seu nome
	 * @param name indica o nome do contacto a consultar o telefone
	 * @return o numero de telefone do contacto associado ao nome dado
	 * @throws ContactDoesNotExistsException se o contacto nao existir na agenda.
	 * @pre hasContact(name)
	 */
	int getPhone(String name) throws ContactDoesNotExistsException;

	/**
	 * Consulta o email de um contacto dado o seu nome
	 * @param name indica o nome do contacto a consultar o telefone
	 * @return o email do contacto associado ao nome dado
	 * @throws ContactDoesNotExistsException se o contacto nao existir na agenda.
	 * @pre hasContact(name)
	 */
	String getEmail(String name) throws ContactDoesNotExistsException;

	/**
	 * Altera o telefone de um dado contacto
	 * @param name indica o nome do contacto a moficar o telefone
	 * @param phone indica o novo numero de telefone
	 * @throws ContactDoesNotExistsException se o contacto nao existir na agenda.
	 * @pre hasContact(name)
	 */
	void setPhone(String name, int phone) throws ContactDoesNotExistsException;
	
	/**
	 * Altera o email de um dado contacto
	 * @param name indica o nome do contacto a moficar o email
	 * @param email indica o novo email
	 * @throws ContactDoesNotExistsException se o contacto nao existir na agenda.
	 * @pre hasContact(name)
	 */
	void setEmail(String name, String email) throws ContactDoesNotExistsException;

	/**
	 * Devolve um iterador para os contactos
	 * @return um iterador para os contactos
	 * @throws NoContactsException se nao existirem contactos na agenda.
	 */
	Iterator<Contact> listContacts() throws NoContactsException;

}
