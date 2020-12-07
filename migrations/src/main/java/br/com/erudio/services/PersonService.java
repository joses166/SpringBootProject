package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.converter.custom.PersonConverter;
import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.PersonVO;
import br.com.erudio.data.vo.PersonVOV2;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.repository.PersonRepository;

@Service // Cuida da injeção de dependência - Faz funcionar o Autowired
public class PersonService {
	
	@Autowired
	private PersonRepository repository;
	
	@Autowired
	private PersonConverter converter;
	
	public PersonVO create(PersonVO person) {
		var entity = DozerConverter.parseObject(person, Person.class);
		var vo = DozerConverter.parseObject(this.repository.save(entity), PersonVO.class);
		return vo;
	}
	
	public PersonVOV2 createV2(PersonVOV2 person) {
		var entity = converter.convertVOToEntity(person);
		var vo = converter.convertEntityToVO(this.repository.save(entity));
		return vo;
	}
	
	public List<PersonVO> findAll() {
		var VOList = DozerConverter.parseListObjects(this.repository.findAll(), PersonVO.class);
		return VOList;
	}
	
	public PersonVO findById(Long id) {
		var entity = this.repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO update(PersonVO person) {
		Person entity = this.repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		
		var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	
	public void delete(Long id) {
		Person entity = this.repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
		this.repository.delete(entity);
	}

}