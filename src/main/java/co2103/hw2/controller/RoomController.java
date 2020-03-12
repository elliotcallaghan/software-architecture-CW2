package co2103.hw2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co2103.hw2.domain.Hotel;
import co2103.hw2.domain.Room;
import co2103.hw2.repository.HotelRepository;

@Controller
@RequestMapping("/r/")
public class RoomController {

	@Autowired
	private HotelRepository hrepo;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new RoomValidator());
	}

	@GetMapping("/rooms")
	public String showHotels(@RequestParam String hotel, Model model) {
		model.addAttribute("rooms", hrepo.findByName(hotel).getRooms());
		model.addAttribute("hotel", hotel);
		return "rooms/list";
	}

	@RequestMapping("/newRoom")
	public String newHotel(@RequestParam String hotel, Model model) {
		model.addAttribute("room", new Room());
		model.addAttribute("hotel", hotel);
		return "rooms/form";
	}

	@PostMapping("/addRoom")
	public String newHotel(@RequestParam String hotel, @Valid @ModelAttribute Room room, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("hotel", hotel);
			return "rooms/form";
		}
		Hotel h = hrepo.findByName(hotel);
		h.getRooms().add(room);
		h = hrepo.save(h);
		return "redirect:/h/hotels";
	}
}
