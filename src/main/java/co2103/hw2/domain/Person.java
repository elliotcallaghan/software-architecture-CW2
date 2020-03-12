package co2103.hw2.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Used to create person objects. They can be guests, staff or managers.
 * @author calla
 */
@Entity
public class Person {
	/**
	 * Username of the person
	 */
	@Id
	private String username;
	
	/**
	 * Full name of the person
	 */
	private String fullName;
	
	/**
	 * Password of the person, will be encoded on creation.
	 */
	private String password;
	
	/**
	 * Role of the user. Can be a guest, staff or a manager.
	 */
	private UserKind kind;

	public Person() {
		super();
	}

	public Person(String fullName, String username, String password, UserKind kind) {
		this.fullName = fullName;
		this.kind = kind;
		this.username = username;
		this.password = password;
	}

	public UserKind getKind() {
		return kind;
	}

	public void setKind(UserKind kind) {
		this.kind = kind;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {	
		return fullName;
	}
}
