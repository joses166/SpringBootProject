package br.com.erudio.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.services.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@CrossOrigin(origins = "*")
@Api(value = "API Book", description = "Description for book", tags = {"Book Endpoint"})
@RestController
@RequestMapping("/api/book/v1")
public class BookController {

	@Autowired
	private BookService bookService;
	
	// @CrossOrigin(origins = "http://localhost:8080")
	@ApiOperation(value = "Find all books recorded")
	@GetMapping(produces = { "application/xml", "application/json", "application/x-yaml" })
	public List<BookVO> findAll() {
		List<BookVO> books = this.bookService.findAll();
		books.stream().forEach(item -> {
			item.add(linkTo(methodOn(BookController.class).findById(item.getKey())).withSelfRel());
		});
		return books;
	}
	
	@ApiOperation(value = "Find by id a specific book recorded")
	@GetMapping(
			value = "{id}", 
			produces = { "application/xml", "application/json", "application/x-yaml" }
			)
	public BookVO findById(@PathVariable("id") Long id) {
		BookVO bookVO = this.bookService.findById(id);
		bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
		return bookVO;
	}
	
	@ApiOperation(value = "Create a book")
	@PostMapping(
			consumes = { "application/xml", "application/json", "application/x-yaml" },
			produces = { "application/xml", "application/json", "application/x-yaml" }
			)
	public BookVO create(@RequestBody BookVO bookVO) {
		bookVO = this.bookService.create(bookVO);
		bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
		return bookVO;
	}
	
	@ApiOperation(value = "Update a book")
	@PutMapping(
			consumes = { "application/xml", "application/json", "application/x-yaml" },
			produces = { "application/xml", "application/json", "application/x-yaml" }
			)
	public BookVO update(@RequestBody BookVO bookVO) {
		bookVO = this.bookService.create(bookVO);
		bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
		return bookVO;
	}
	
	@ApiOperation(value = "Delete a book")
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		this.bookService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
