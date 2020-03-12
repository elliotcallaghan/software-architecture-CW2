
package co2103.hw2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import co2103.hw2.domain.Booking;
import co2103.hw2.domain.Hotel;
import co2103.hw2.domain.Person;
import co2103.hw2.domain.UserKind;
import co2103.hw2.repository.HotelRepository;
import co2103.hw2.repository.PersonRepository;

@SpringBootApplication
public class Hw2Application implements ApplicationRunner {
	
	@Autowired
	private HotelRepository hrepo;
	
	@Autowired
	private PersonRepository prepo;
	
	@Autowired
	private PasswordEncoder pe;
	
	public static Person user;

	public static void main(String[] args) {
		SpringApplication.run(Hw2Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Hotel h1 = new Hotel("Hilton", "by the lake");
		Hotel h2 = new Hotel("Ibis", "behind the station");
		
		user = new Person("Manfred", "manager", pe.encode("password"), UserKind.Manager);
		user = prepo.save(user);

		Person p1 = new Person("Steven", "staff1", pe.encode("password"), UserKind.Staff);
		p1 = prepo.save(p1);
		h1.getStaff().add(p1);

		Person p2 = new Person("Stas", "staff2", pe.encode("password"), UserKind.Staff);
		p2 = prepo.save(p2);
		h2.getStaff().add(p2);

		Person p3 = new Person("Gavin", "guest", pe.encode("password"), UserKind.Guest);
		p3 = prepo.save(p3);

		Booking b = new Booking();
		b.setHotel(h1);
		h1.getBookings().add(b);
		b.getGuests().add(p3);;
		
		h1 = hrepo.save(h1);
		h2 = hrepo.save(h2);
	}

}
