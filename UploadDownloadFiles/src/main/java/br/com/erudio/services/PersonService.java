package br.com.erudio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.repository.PersonRepository;

@Service // Cuida da injeção de dependência - Faz funcionar o Autowired
public class PersonService {
	
	@Autowired
	private PersonRepository repository;
	
	public PersonVO create(PersonVO person) {
		var entity = DozerConverter.parseObject(person, Person.class);
		var vo = DozerConverter.parseObject(this.repository.save(entity), PersonVO.class);
		return vo;
	}
	
//	public List<PersonVO> findAll(Pageable pageable) {
//		var VOList = DozerConverter.parseListObjects(this.repository.findAll(), PersonVO.class);
//		return VOList;
//	}
	
//	public List<PersonVO> findAll(Pageable pageable) {
//		var entities = this.repository.findAll(pageable).getContent();
//		var VOList = DozerConverter.parseListObjects(entities, PersonVO.class);
//		return VOList;
//	}
	
	public Page<PersonVO> findPersonByName(String firstName, Pageable pageable) {
		var entities = this.repository.findPersonByName(firstName, pageable);
		return entities.map(this::convertToPersonVO); // Faz uma interação dos dados convertendo para o PersonVO
	}
	
	public Page<PersonVO> findAll(Pageable pageable) {
		var entities = this.repository.findAll(pageable);
		return entities.map(this::convertToPersonVO); // Faz uma interação dos dados convertendo para o PersonVO
	}
	
	public PersonVO convertToPersonVO(Person entity) {
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO findById(Long id) {
		var entity = this.repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO update(PersonVO person) {
		Person entity = this.repository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	
	@Transactional
	public PersonVO disablePerson(Long id) {
		this.repository.disablePerson(id);
		
		var entity = this.repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
	
	public void delete(Long id) {
		Person entity = this.repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
		this.repository.delete(entity);
	}

}