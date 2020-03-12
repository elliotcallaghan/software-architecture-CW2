package co2103.hw2.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Used to create booking objects, including guests staying at the hotel.
 * @author calla
 */
@Entity
public class Booking {
	/**
	 * Generated id of booking.
	 */
	@Id
	@GeneratedValue
	int id;
	
	/**
	 * Start date for booking in "yyyy-MM-dd" format.
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date start = Calendar.getInstance().getTime();
	
	/**
	 * End date for booking in "yyyy-MM-dd" format.
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date end = Calendar.getInstance().getTime();;

	/**
	 * ArrayList of guests belonging to booking, creates new associative table.
	 * Guests are saved when saving booking object.
	 */
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Person> guests = new ArrayList<>();

	/**
	 * Hotel used for booking, hotel is saved whenever it is changed.
	 */
	@ManyToOne(cascade = CascadeType.MERGE)
	private Hotel hotel;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;		
	}

	public List<Person> getGuests() {
		return guests;
	}

	public void setGuests(List<Person> guests) {
		this.guests = guests;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
}
