package br.com.erudio.request.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.erudio.data.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
