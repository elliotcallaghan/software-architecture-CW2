package co2103.hw2.repository;

import org.springframework.data.repository.CrudRepository;

import co2103.hw2.domain.Hotel;

public interface HotelRepository extends CrudRepository<Hotel, String> {

	public Hotel findByName(String name);
}
