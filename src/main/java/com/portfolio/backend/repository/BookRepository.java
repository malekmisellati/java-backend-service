
package com.portfolio.backend.repository;

import com.portfolio.backend.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
