package br.com.erudio.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exception.InvalidInformationException;
import br.com.erudio.services.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

// @CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/person/v1")
@Api(
		value = "API Person", 
		tags = {"Person Endpoint"}
		)
public class PersonController {

	@Autowired
	private PersonService services;
	
	@Autowired
	private PagedResourcesAssembler<PersonVO> assembler;

	// @CrossOrigin(origins = "http://localhost:8080")
//	@ApiOperation(value = "Find all people recorded")
//	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
//	public List<PersonVO> findAll() {
//		List<PersonVO> people = services.findAll();
//		people.stream().forEach(item -> {
//			item.add(linkTo(methodOn(PersonController.class).findById(item.getKey())).withSelfRel());
//		});
//		return people;
//	}
	
	@ApiOperation(value = "Find all people recorded WITH pagination and filtering by first name")
	@GetMapping(value = "/findPersonByName/{firstName}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findPersonByName(
			@PathVariable("firstName") String firstName,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "0") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction
			) {
		
		// Criar variável de sortAttribute = para ordernar de acordo com atributo informado. defaultValue=firstName
		
		Direction sortDirection;
		
		if ("desc".equalsIgnoreCase(direction)) sortDirection = Direction.DESC;
		else if ("asc".equalsIgnoreCase(direction)) sortDirection = Direction.ASC;
		else throw new InvalidInformationException("The direction is incorret!");
				 		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName")); // Informa a direção e os atributos
		
		Page<PersonVO> people = services.findPersonByName(firstName, pageable);
		
		people.stream().forEach(item -> {
			item.add(linkTo(methodOn(PersonController.class).findById(item.getKey())).withSelfRel());
		});
		
		PagedResources<?> resources = assembler.toResource(people);
		return ResponseEntity.ok(resources);
		
	}
	
	@ApiOperation(value = "Find all people recorded WITH pagination")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "0") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction
			) {
		
		// Criar variável de sortAttribute = para ordernar de acordo com atributo informado. defaultValue=firstName
		
		Direction sortDirection;
		
		if ("desc".equalsIgnoreCase(direction)) sortDirection = Direction.DESC;
		else if ("asc".equalsIgnoreCase(direction)) sortDirection = Direction.ASC;
		else throw new InvalidInformationException("The direction is incorret!");
				 		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName")); // Informa a direção e os atributos
		
		Page<PersonVO> people = services.findAll(pageable);
		
		people.stream().forEach(item -> {
			item.add(linkTo(methodOn(PersonController.class).findById(item.getKey())).withSelfRel());
		});
		
		PagedResources<?> resources = assembler.toResource(people);
		return ResponseEntity.ok(resources);
	}
	
	@ApiOperation(value = "Find by id specific person recorded")
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO findById(@PathVariable("id") Long id) {
		PersonVO personVO = services.findById(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	
	@ApiOperation(value = "Create a new person")
	@PostMapping(
			produces = {"application/json", "application/xml", "application/x-yaml"}, 
			consumes = {"application/json", "application/xml", "application/x-yaml"}
			)
	public PersonVO create(@RequestBody PersonVO personVO) {
		personVO = services.create(personVO);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}
	
	@ApiOperation(value = "Update a person")
	@PutMapping(
			produces = {"application/json", "application/xml", "application/x-yaml"}, 
			consumes = {"application/json", "application/xml", "application/x-yaml"}
			)
	public PersonVO update(@RequestBody PersonVO PersonVO) {
		PersonVO personVO = services.update(PersonVO);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}
	
	@ApiOperation(value = "Disable person with a specific id")
	@PatchMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO disablePerson(@PathVariable("id") Long id) {
		PersonVO personVO = services.disablePerson(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	
	@ApiOperation(value = "Delete a person")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		services.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}