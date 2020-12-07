package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public List<BookVO> findAll() {
		var books = this.bookRepository.findAll();
		var booksVO = DozerConverter.parseListObjects(books, BookVO.class);
		return booksVO;
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
