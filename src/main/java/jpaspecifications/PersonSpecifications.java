package jpaspecifications;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import static jpaspecifications.Person.PROPERTYNAME_BIRTHDATE;
import static jpaspecifications.Person.PROPERTYNAME_FIRSTNAME;
import static jpaspecifications.Person.PROPERTYNAME_LASTNAME;

public class PersonSpecifications {

	public static Specification<Person> lastnameContains(String content) {
		return contains(PROPERTYNAME_LASTNAME, content);
	}
	
	public static Specification<Person> firstnameContains(String content) {
		return contains(PROPERTYNAME_FIRSTNAME, content);
	}

	public static Specification<Person> isBornOn(LocalDate date) {
		return (root, query, cb) -> cb.equal(root.get(PROPERTYNAME_BIRTHDATE), date);
	}
	
	private static Specification<Person> contains(String propertyname, String content) {
		return (root, query, cb) -> cb.like(cb.lower(root.get(propertyname)), "%" + content.toLowerCase() + "%");
	}
}
