
package com.portfolio.backend.service;

import com.portfolio.backend.exception.BookNotFoundException;
import com.portfolio.backend.model.Book;
import com.portfolio.backend.repository.BookRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {

        Book existingBook = getBookById(id);

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setPublicationYear(updatedBook.getPublicationYear());

        return bookRepository.save(existingBook);
    }

    public boolean deleteBook(Long id) {

        Book book = getBookById(id);

        bookRepository.delete(book);

        return true;
    }
}
