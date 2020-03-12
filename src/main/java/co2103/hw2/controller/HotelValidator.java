package co2103.hw2.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import co2103.hw2.domain.Hotel;
import co2103.hw2.repository.HotelRepository;

public class HotelValidator implements Validator {

	private HotelRepository hrepo;
	
	public HotelValidator(HotelRepository hrepo) {
		this.hrepo = hrepo;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Hotel.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "", "required");

		Hotel hotel = (Hotel) target;
			
		if (hrepo.existsById(hotel.getName())) {
			errors.rejectValue("name", "", "taken");
		}
	}

}
