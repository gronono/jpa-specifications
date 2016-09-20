package jpaspecifications;

import java.time.LocalDate;

import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class PersonSpecifications {

	public static Specification<Person> lastnameContains(String content) {
		return contains(Person_.lastname, content);
	}

	public static Specification<Person> firstnameContains(String content) {
		return contains(Person_.firstname, content);
	}

	public static Specification<Person> isBornOn(LocalDate date) {
		return (root, query, cb) -> cb.equal(root.get(Person_.birthdate), date);
	}

	private static Specification<Person> contains(SingularAttribute<Person, String> propertyname, String content) {
		return !StringUtils.hasText(content) ? null
				: (root, query, cb) -> cb.like(cb.lower(root.get(propertyname)), "%" + content.toLowerCase() + "%");
	}
}
