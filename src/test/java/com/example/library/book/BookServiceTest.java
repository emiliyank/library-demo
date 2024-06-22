package com.example.library.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
    }

    @Test
    void testSaveBook() {
        // Arrange
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        // Act
        Book savedBook = bookService.saveBook(book);

        // Assert
        assertNotNull(savedBook);
        assertEquals(1L, savedBook.getId());
        assertEquals("Test Book", savedBook.getTitle());
        assertEquals("Test Author", savedBook.getAuthor());

        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testGetAllBooks() {
        // Arrange
        List<Book> booksList = new ArrayList<>();
        booksList.add(book);
        when(bookRepository.findAll()).thenReturn(booksList);

        // Act
        List<Book> result = bookService.getAllBooks();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Book", result.get(0).getTitle());
        assertEquals("Test Author", result.get(0).getAuthor());

        verify(bookRepository, times(1)).findAll();
    }
}

