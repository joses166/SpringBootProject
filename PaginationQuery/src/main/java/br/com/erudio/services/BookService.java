package br.com.erudio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.data.model.Book;
import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	public Page<BookVO> findAll(Pageable pageable) {
		var books = this.bookRepository.findAll(pageable);
		return books.map(this::convertToBookVO);
	}
	
	private BookVO convertToBookVO(Book entity) {
		return DozerConverter.parseObject(entity, BookVO.class);
	}
	
	public BookVO findById(Long id) {
		var book = this.bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
		var bookVO = DozerConverter.parseObject(book, BookVO.class);
		return bookVO;
	}
	
	public BookVO create(BookVO book) {
		var entity = DozerConverter.parseObject(book, Book.class);
		var bookVO = DozerConverter.parseObject(bookRepository.save(entity), BookVO.class);
		return bookVO;
	}
	
	public BookVO update(Book book) {
		var found = this.bookRepository.findById(book.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
		
		found.setId(book.getId());
		found.setAuthor(book.getAuthor());
		found.setLaunchDate(book.getLaunchDate());
		found.setPrice(book.getPrice());
		found.setTitle(book.getTitle());
		
		var bookVO = DozerConverter.parseObject(this.bookRepository.save(found), BookVO.class);
		return bookVO;
	}
	
	public void delete(Long id) {
		var found = this.bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
		this.bookRepository.delete(found);
	}
	
}
