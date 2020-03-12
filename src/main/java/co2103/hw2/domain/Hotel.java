package co2103.hw2.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * Used to create hotel objects, including info about it's rooms and staff.
 * @author calla
 *
 */
@Entity
public class Hotel {
	/**
	 * Name of the hotel.
	 */
	@Id
	private String name;
	
	/**
	 * Description of the hotel.
	 */
	private String description;

	/**
	 * ArrayList of rooms objects belonging to the hotel,
	 * all operations performed on this object cascade to rooms.
	 */
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "belongs_to")
	private List<Room> rooms = new ArrayList<>();

	/**
	 * ArrayList of staff from Person class belonging to the hotel.
	 */
	@OneToMany
	@JoinColumn(name = "belongs_to")
	private List<Person> staff = new ArrayList<>();
	
	/**
	 * ArrayList of booking objects made at the hotel, bi-directional relationship.
	 * Bookings is saved when saving hotel and when booking object is changed.
	 */
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "hotel")
	private List<Booking> bookings = new ArrayList<>();
	
	public Hotel() {
	}

	public Hotel(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public List<Person> getStaff() {
		return staff;
	}

	public void setStaff(List<Person> staff) {
		this.staff = staff;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

}
