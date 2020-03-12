package co2103.hw2.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co2103.hw2.domain.Booking;
import co2103.hw2.domain.Hotel;
import co2103.hw2.domain.Person;
import co2103.hw2.domain.UserKind;
import co2103.hw2.repository.BookingRepository;
import co2103.hw2.repository.HotelRepository;
import co2103.hw2.repository.PersonRepository;

/**
 * Controller for viewing, creating and deleting bookings.
 * Can only be accessed by staff and guests.
 * @author calla
 */
@Controller
@RequestMapping("/b/")
public class BookingController {
	/**
	 * Repository used to store bookings.
	 * Contains methods findByHotel_Staff(Person p) and findByGuests(Person p).
	 */
	@Autowired
	private BookingRepository brepo;
	
	/**
	 * Repository used to store hotels.
	 * Contains method findByName(String name).
	 */
	@Autowired
	private HotelRepository hrepo;
	
	/**
	 * Repository used to store people.
	 * Contains method findByUsername(String name).
	 */
	@Autowired
	private PersonRepository prepo;
	
	/**
	 * Lists booking objects. For staff, lists only bookings at their hotel and
	 * for guests only the bookings they have made.
	 * @param model Used to pass list of bookings.
	 * @param principal Used to check the role of the person logged in.
	 * @return jsp showing all the bookings passed as an attribute.
	 */
	@GetMapping("/bookings")
	public String showBookings(Model model, Principal principal) {
		Person p = prepo.findByUsername(principal.getName());
		if (p.getKind() == UserKind.Staff) {
			model.addAttribute("bookings", brepo.findByHotel_Staff(p));
		} else if (p.getKind() == UserKind.Guest) {
			model.addAttribute("bookings", brepo.findByGuests(p));
		}
		return "bookings/list";
	}

	/**
	 * Shows form for adding bookings at specific hotel.
	 * @param model Used to add list of hotel names as an attribute.
	 * @return jsp form for adding booking info.
	 */
	@RequestMapping("/newBooking")
	public String newBooking(Model model) {
		List<String> hotelNames = new ArrayList<>();
		
		for (Hotel hotel: hrepo.findAll()) {
			hotelNames.add(hotel.getName());
		}
		
		model.addAttribute("hotels", hotelNames);
		model.addAttribute("booking", new Booking());
		return "bookings/form";
	}

	/**
	 * Adds current user to the booking as a guest and saves booking to BookingRepository.
	 * @param booking New booking object to have information added to it.
	 * @param hotelName Name of the hotel the booking is being made at.
	 * @param principal Used to add logged in user as a guest to the booking.
	 * @return Redirect to showBookings() to list bookings.
	 */
	@PostMapping("/addBooking")
	public String addBooking(@ModelAttribute Booking booking, @RequestParam String hotelName, Principal principal) {
		booking.setHotel(hrepo.findByName(hotelName));
		booking.getGuests().add(prepo.findByUsername(principal.getName()));
		booking.setId((int) System.currentTimeMillis());
		booking = brepo.save(booking);
		return "redirect:/b/bookings";
	}
	
	/**
	 * Deletes a booking from BookingRepository.
	 * @param id Id of the booking to be deleted.
	 * @return Redirect to showBookings() to list bookings.
	 */
	@GetMapping("/deleteBooking")
	public String deleteBooking(@RequestParam int id) {
		brepo.deleteById(id);
		return "redirect:/b/bookings";
	}
}
