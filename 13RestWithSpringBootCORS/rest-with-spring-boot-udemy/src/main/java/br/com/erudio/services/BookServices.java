package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.data.model.Book;
import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.request.repository.BookRepository;
import br.com.erudio.request.repository.PersonRepository;

@Service
public class BookServices {

	@Autowired
	private BookRepository repository;

	public BookVO create(BookVO book) {
		Book entity = DozerConverter.parseObject(book, Book.class);
		return DozerConverter.parseObject(repository.save(entity), BookVO.class);
	}

	public BookVO update(BookVO book) {
		Book entity = DozerConverter.parseObject(findById(book.getKey()), Book.class);
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		return DozerConverter.parseObject(repository.save(entity), BookVO.class);
	}

	public void delete(Long id) {
		repository.delete(DozerConverter.parseObject(findById(id), Book.class));
	}

	public BookVO findById(Long id) {
		Book entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, BookVO.class);
	}

	public List<BookVO> findAll() {
		return DozerConverter.parseListObjects(repository.findAll(), BookVO.class);
	}
}
