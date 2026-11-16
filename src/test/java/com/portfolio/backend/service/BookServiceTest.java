package com.portfolio.backend.service;
import com.portfolio.backend.model.Book;
import com.portfolio.backend.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import com.portfolio.backend.exception.BookNotFoundException;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testGetAllBooks() {

        // Arrange: Create sample books
        List<Book> books = List.of(
                new Book(1L, "The Hobbit", "J.R.R. Tolkien", 1937),
                new Book(2L, "1984", "George Orwell", 1949)
        );

        // Mock the repository response
        when(bookRepository.findAll()).thenReturn(books);

        // Act: Call the service method
        List<Book> result = bookService.getAllBooks();

        // Assert: Verify the result
        assertEquals(2, result.size());
        assertEquals("The Hobbit", result.get(0).getTitle());
        assertEquals("1984", result.get(1).getTitle());
    }


@Test
public void testGetBookById() {

    // Arrange
    Book book = new Book(
            1L,
            "The Hobbit",
            "J.R.R. Tolkien",
            1937
    );

    when(bookRepository.findById(1L))
            .thenReturn(java.util.Optional.of(book));

    // Act
    Book result = bookService.getBookById(1L);

    // Assert
    assertEquals(1L, result.getId());
    assertEquals("The Hobbit", result.getTitle());
    assertEquals("J.R.R. Tolkien", result.getAuthor());
}


@Test
public void testAddBook() {

    // Arrange
    Book book = new Book(
            null,
            "Dune",
            "Frank Herbert",
            1965
    );

    Book savedBook = new Book(
            1L,
            "Dune",
            "Frank Herbert",
            1965
    );

    when(bookRepository.save(book)).thenReturn(savedBook);

    // Act
    Book result = bookService.addBook(book);

    // Assert
    assertEquals(1L, result.getId());
    assertEquals("Dune", result.getTitle());
    assertEquals("Frank Herbert", result.getAuthor());
    assertEquals(1965, result.getPublicationYear());
}


@Test
public void testUpdateBook() {

    // Arrange
    Book existingBook = new Book(
            1L,
            "The Hobbit",
            "J.R.R. Tolkien",
            1937
    );

    Book updatedBook = new Book(
            null,
            "The Hobbit",
            "J.R.R. Tolkien",
            1938
    );

    when(bookRepository.findById(1L))
            .thenReturn(java.util.Optional.of(existingBook));

    when(bookRepository.save(existingBook))
            .thenReturn(existingBook);

    // Act
    Book result = bookService.updateBook(1L, updatedBook);

    // Assert
    assertEquals(1938, result.getPublicationYear());
    assertEquals("The Hobbit", result.getTitle());
}


@Test
public void testDeleteBook() {

    // Arrange
    Book book = new Book(
            1L,
            "The Hobbit",
            "J.R.R. Tolkien",
            1937
    );

    when(bookRepository.findById(1L))
            .thenReturn(java.util.Optional.of(book));

    // Act
    boolean result = bookService.deleteBook(1L);

    // Assert
    assertEquals(true, result);

    org.mockito.Mockito.verify(bookRepository)
            .delete(book);
}


@Test
public void testGetBookByIdNotFound() {

    // Arrange
    when(bookRepository.findById(99L))
            .thenReturn(java.util.Optional.empty());

    // Act and Assert
    BookNotFoundException exception = assertThrows(
            BookNotFoundException.class,
            () -> bookService.getBookById(99L)
    );

    assertEquals(
            "Book not found with ID: 99",
            exception.getMessage()
    );
}


@Test
public void testDeleteBookNotFound() {

    // Arrange
    when(bookRepository.findById(99L))
            .thenReturn(java.util.Optional.empty());

    // Act and Assert
    BookNotFoundException exception = assertThrows(
            BookNotFoundException.class,
            () -> bookService.deleteBook(99L)
    );

    assertEquals(
            "Book not found with ID: 99",
            exception.getMessage()
    );
}

}
