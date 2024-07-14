package com.example.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    //@Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book updateBook(Long id, Book book) {
        if (bookRepository.existsById(id)) {
            book.setId(id);
            return bookRepository.save(book);
        } else {
            return null;
        }
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
