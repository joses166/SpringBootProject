package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repository.PersonRepository;

@Service // Cuida da injeção de dependência - Faz funcionar o Autowired
public class PersonService {
	
	@Autowired
	private PersonRepository repository;
	
	public Person create(Person person) {
		return this.repository.save(person);
	}
	
	public List<Person> findAll() {
		return this.repository.findAll();
	}
	
	public Person findById(Long id) {
		return this.repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
	}
	
	public Person update(Person person) {
		Person entity = this.repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return this.repository.save(entity);
	}
	
	public void delete(Long id) {
		Person entity = this.repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
		this.repository.delete(entity);;
	}

}