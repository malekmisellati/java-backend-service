package com.portfolio.backend.controller;

import com.portfolio.backend.model.Book;
import com.portfolio.backend.service.BookService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/api/books")
    public List<Book> getAllBooks() {
        
        return bookService.getAllBooks();
    }

    @GetMapping("/api/books/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);

    }
    @PostMapping("/api/books")
public Book createBook(@RequestBody Book book) {
    return bookService.addBook(book);
}
    @PutMapping("/api/books/{id}")
public Book updateBook(
        @PathVariable Long id,
        @RequestBody Book book) {

    return bookService.updateBook(id, book);

}
@DeleteMapping("/api/books/{id}")
public boolean deleteBook(@PathVariable Long id) {
    return bookService.deleteBook(id);
}
}
