package jpaspecifications;

import java.time.LocalDate;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.springframework.data.jpa.domain.Specifications.where;
import static java.time.LocalDate.now;
import static jpaspecifications.PersonSpecifications.firstnameContains;
import static jpaspecifications.PersonSpecifications.isBornOn;
import static jpaspecifications.PersonSpecifications.lastnameContains;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {JpaSpecifications.class})
public class PersonSpecificationsTest {

	@Autowired
	private PersonRepository personRepository;

	private Person gerard;
	@Before
	public void before() {
		this.personRepository.deleteAll();
		
		this.gerard = this.personRepository.save(new Person("Gérard", "Bouchard", LocalDate.of(1982, Calendar.MARCH, 24)));
		this.personRepository.save(new Person("Robert", "Martin", now()));
	}
	
	@Test
	public void testLastnameContains() {
		assertEquals(this.gerard, this.personRepository.findOne(lastnameContains("Bouchard")));
		assertEquals(this.gerard, this.personRepository.findOne(lastnameContains("Bouc")));
		assertEquals(this.gerard, this.personRepository.findOne(lastnameContains("ard")));
		assertEquals(this.gerard, this.personRepository.findOne(lastnameContains("bouCHARD")));
	}
	
	@Test
	public void testFirstnameContains() {
		assertEquals(this.gerard, this.personRepository.findOne(firstnameContains("Gérard")));
		assertEquals(this.gerard, this.personRepository.findOne(firstnameContains("Gér")));
		assertEquals(this.gerard, this.personRepository.findOne(firstnameContains("ard")));
		assertEquals(this.gerard, this.personRepository.findOne(firstnameContains("géRarD")));
		// FIXME
		//assertEquals(this.gerard, this.personRepository.findOne(firstnameContains("gerard")));
	}
	
	@Test
	public void testIsBornOn() {
		assertEquals(this.gerard, this.personRepository.findOne(isBornOn(this.gerard.getBirthdate())));
	}
	
	@Test
	public void test() {
		assertEquals(this.gerard, this.personRepository.findOne(where(isBornOn(this.gerard.getBirthdate())).and(firstnameContains("gérard"))));
	}
}
