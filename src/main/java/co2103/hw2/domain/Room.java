package co2103.hw2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Used to create room objects. Is a child class of hotel.
 * @author calla
 */
@Entity
public class Room {
	/**
	 * Generated id of the room.
	 */
	@Id
	@GeneratedValue
	private int id;
	
	/**
	 * Description of the room.
	 */
	private String description;
	
	/**
	 * Category of the room.
	 */
	private String category;
	
	/**
	 * The max number of guests that the room can contain.
	 */
	private int maxGuests;

	public Room() {
		super();
	}

	public Room(String description, String category, int maxGuests) {
		this.category = category;
		this.description = description;
		this.maxGuests = maxGuests;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getMaxGuests() {
		return maxGuests;
	}

	public void setMaxGuests(int maxGuests) {
		this.maxGuests = maxGuests;
	}

}
