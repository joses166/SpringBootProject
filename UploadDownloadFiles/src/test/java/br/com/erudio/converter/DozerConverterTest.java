package br.com.erudio.converter;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.erudio.converter.mocks.MockBook;
import br.com.erudio.converter.mocks.MockPerson;
import br.com.erudio.data.model.Book;
import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.data.vo.v1.PersonVO;

public class DozerConverterTest {

	MockPerson inputObject;
	MockBook inputBookObject;
	
	@Before
	public void setUp() {
		this.inputObject = new MockPerson();
		this.inputBookObject = new MockBook();
	}
	
	@Test
	public void parseEntityToVOTest() {
		PersonVO output = DozerConverter.parseObject(inputObject.mockEntity(), PersonVO.class);
		Assert.assertEquals( Long.valueOf(0L), output.getKey() );
		Assert.assertEquals( "Address Test0", output.getAddress() );
		Assert.assertEquals( "First Name Test0", output.getFirstName() );
		Assert.assertEquals( "Last Name Test0", output.getLastName() );
		Assert.assertEquals( "Gender Test0", output.getGender() );
	}
	
	@Test
	public void parseEntityListToVOListTest() {
		List<PersonVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), PersonVO.class);
		
		PersonVO outputZero = outputList.get(0);
		Assert.assertEquals( Long.valueOf(0L), outputZero.getKey() );
		Assert.assertEquals( "Address Test0", outputZero.getAddress() );
		Assert.assertEquals( "First Name Test0", outputZero.getFirstName() );
		Assert.assertEquals( "Last Name Test0", outputZero.getLastName() );
		Assert.assertEquals( "Gender Test0", outputZero.getGender() );
		
		PersonVO outputSete = outputList.get(7);
		Assert.assertEquals( Long.valueOf(7L), outputSete.getKey() );
		Assert.assertEquals( "Address Test7", outputSete.getAddress() );
		Assert.assertEquals( "First Name Test7", outputSete.getFirstName() );
		Assert.assertEquals( "Last Name Test7", outputSete.getLastName() );
		Assert.assertEquals( "Gender Test7", outputSete.getGender() );
		
		PersonVO outputDoze = outputList.get(12);
		Assert.assertEquals( Long.valueOf(12L), outputDoze.getKey() );
		Assert.assertEquals( "Address Test12", outputDoze.getAddress() );
		Assert.assertEquals( "First Name Test12", outputDoze.getFirstName() );
		Assert.assertEquals( "Last Name Test12", outputDoze.getLastName() );
		Assert.assertEquals( "Gender Test12", outputDoze.getGender() );
	}
	
	@Test
	public void parseVOToEntityTest() {
		Person output = DozerConverter.parseObject(inputObject.mockVO(), Person.class);
		Assert.assertEquals( Long.valueOf(0L), output.getId() );
		Assert.assertEquals( "Address Test0", output.getAddress() );
		Assert.assertEquals( "First Name Test0", output.getFirstName() );
		Assert.assertEquals( "Last Name Test0", output.getLastName() );
		Assert.assertEquals( "Gender Test0", output.getGender() );
	}
	
	@Test
	public void parseVOListToEntityListTest() {
		List<Person> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(), Person.class);
		
		Person outputZero = outputList.get(0);
		Assert.assertEquals( Long.valueOf(0L), outputZero.getId() );
		Assert.assertEquals( "Address Test0", outputZero.getAddress() );
		Assert.assertEquals( "First Name Test0", outputZero.getFirstName() );
		Assert.assertEquals( "Last Name Test0", outputZero.getLastName() );
		Assert.assertEquals( "Gender Test0", outputZero.getGender() );
		
		Person outputSete = outputList.get(7);
		Assert.assertEquals( Long.valueOf(7L), outputSete.getId() );
		Assert.assertEquals( "Address Test7", outputSete.getAddress() );
		Assert.assertEquals( "First Name Test7", outputSete.getFirstName() );
		Assert.assertEquals( "Last Name Test7", outputSete.getLastName() );
		Assert.assertEquals( "Gender Test7", outputSete.getGender() );
		
		Person outputDoze = outputList.get(12);
		Assert.assertEquals( Long.valueOf(12L), outputDoze.getId() );
		Assert.assertEquals( "Address Test12", outputDoze.getAddress() );
		Assert.assertEquals( "First Name Test12", outputDoze.getFirstName() );
		Assert.assertEquals( "Last Name Test12", outputDoze.getLastName() );
		Assert.assertEquals( "Gender Test12", outputDoze.getGender() );
	}
	
	// Testes da classe Book
	
	@Test
	public void parseEntityToVOTestBook() {
		BookVO output = DozerConverter.parseObject(inputBookObject.mockEntity(), BookVO.class);
		
		Assert.assertEquals( Long.valueOf(0L), output.getKey() );
		Assert.assertEquals("Author0", output.getAuthor());
		Assert.assertEquals("Title0", output.getTitle());
	}
	
	@Test
	public void parseEntityListToVOListTestBook() {
		List<BookVO> outputList = DozerConverter.parseListObjects(inputBookObject.mockEntityList(), BookVO.class);
		
		BookVO outputZero = outputList.get(0);
		Assert.assertEquals( Long.valueOf(0L), outputZero.getKey() );
		Assert.assertEquals("Author0", outputZero.getAuthor());
		Assert.assertEquals("Title0", outputZero.getTitle());
	}
	
	@Test
	public void parseVOToEntityTestBook() {
		Book output = DozerConverter.parseObject(inputBookObject.mockVO(), Book.class);
		Assert.assertEquals( Long.valueOf(0L), output.getId() );
		Assert.assertEquals("Author0", output.getAuthor());
		Assert.assertEquals("Title0", output.getTitle());
	}
	
	@Test
	public void parseVOListToEntityListTestBook() {
		List<Book> outputList = DozerConverter.parseListObjects(inputBookObject.mockVOList(), Book.class);
		
		Book outputZero = outputList.get(0);
		Assert.assertEquals( Long.valueOf(0L), outputZero.getId() );
		Assert.assertEquals("Author0", outputZero.getAuthor());
		Assert.assertEquals("Title0", outputZero.getTitle());
	}
	
}
