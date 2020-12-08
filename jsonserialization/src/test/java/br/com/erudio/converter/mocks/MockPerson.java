package br.com.erudio.converter.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.v1.PersonVO;

public class MockPerson {

	public Person mockEntity() {
		return mockEntity(0);
	}
	
	public PersonVO mockVO() {
		return mockVO(0);
	}
	
	public List<Person> mockEntityList() {
		List<Person> people = new ArrayList<Person>();
		for (int i = 0; i < 14; i++) {
			people.add(mockEntity(i));
		}
		return people;
	}
	
	public List<PersonVO> mockVOList() {
		List<PersonVO> people = new ArrayList<PersonVO>();
		for (int i = 0; i < 14; i++) {
			people.add(mockVO(i));
		}
		return people;
	}
	
	public Person mockEntity(Integer number) {
		Person person = new Person();
		person.setId(number.longValue());
		person.setAddress("Address Test" + number);
		person.setFirstName("First Name Test" + number);
		person.setLastName("Last Name Test"  + number);
		person.setGender("Gender Test" + number);
		return person;
	}
	
	public PersonVO mockVO(Integer number) {
		PersonVO person = new PersonVO();
		person.setId(number.longValue());
		person.setAddress("Address Test" + number);
		person.setFirstName("First Name Test" + number);
		person.setLastName("Last Name Test"  + number);
		person.setGender("Gender Test" + number);
		return person;
	}
	
}