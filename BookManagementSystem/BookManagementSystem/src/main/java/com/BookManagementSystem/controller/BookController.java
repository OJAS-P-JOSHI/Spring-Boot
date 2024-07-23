package com.BookManagementSystem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.BookManagementSystem.model.Book;
import com.BookManagementSystem.service.BookService2;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService2 bookService;

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        return bookService.deleteBook(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book book) {
        book.setId(id);  // Ensure ID is not updated
        return bookService.updateMyBook(id, book);
    }

    @GetMapping("/title/{title}")
    public List<Book> findBooksByTitle(@PathVariable String title) {
        return bookService.findBooksByTitle(title);
    }

    @GetMapping("/author/{author}")
    public List<Book> findBooksByAuthor(@PathVariable String author) {
        return bookService.findBooksByAuthor(author);
    }
}
