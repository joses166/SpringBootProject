package br.com.erudio.converter.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.PersonVOV2;

@Service
public class PersonConverter {

	public PersonVOV2 convertEntityToVO(Person person) {
		PersonVOV2 vo = new PersonVOV2();
		vo.setId( person.getId() );
		vo.setFirstName( person.getFirstName() );
		vo.setLastName( person.getLastName() );
		vo.setAddress( person.getAddress() );
		vo.setGender( person.getGender() );
		vo.setBirthday( new Date() );
		return vo;
	}
	
	public Person convertVOToEntity(PersonVOV2 personVOV2) {
		Person entity = new Person();
		entity.setId( personVOV2.getId() );
		entity.setFirstName( personVOV2.getFirstName() );
		entity.setLastName( personVOV2.getLastName() );
		entity.setAddress( personVOV2.getAddress() );
		entity.setGender( personVOV2.getGender() );
		return entity;
	}
	
}
