package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.converter.custom.PersonConverter;
import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.request.repository.PersonRepository;

@Service
public class PersonServices {

	@Autowired
	private PersonRepository repository;

	@Autowired
	PersonConverter converter;
	
	public PersonVO create(PersonVO person) {
		Person entity = DozerConverter.parseObject(person, Person.class);
		return DozerConverter.parseObject(repository.save(entity), PersonVO.class);
	}

	public PersonVOV2 createV2(PersonVOV2 person) {
		Person entity = converter.convertVOToEntity(person);
		return converter.convertEntityToVO(repository.save(entity));
	}
	
	public PersonVO update(PersonVO person) {
		Person entity = DozerConverter.parseObject(findById(person.getId()), Person.class);
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		return DozerConverter.parseObject(repository.save(entity), PersonVO.class);
	}

	public void delete(Long id) {
		repository.delete(DozerConverter.parseObject(findById(id), Person.class));
	}

	public PersonVO findById(Long id) {
		Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, PersonVO.class);
	}

	public List<PersonVO> findAll() {
		return DozerConverter.parseListObjects(repository.findAll(), PersonVO.class);
	}
}
