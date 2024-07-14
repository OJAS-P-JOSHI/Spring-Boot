package com.example.bookstore.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.bookstore.model.Book;

@Service // Marks this class as a Spring bean (IoC)
public class BookServiceImpl implements BookService {

    private final Map<Long, Book> bookMap = new HashMap<>();
    private long bookIdCounter = 1;

    @Override
    public Book createBook(Book book) {
        book.setId(bookIdCounter++);
        bookMap.put(book.getId(), book);
        return book;
    }

    @Override
    public Book getBookById(Long id) {
        return bookMap.get(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(bookMap.values());
    }

    @Override
    public Book updateBook(Long id, Book book) {
        if (bookMap.containsKey(id)) {
            book.setId(id);
            bookMap.put(id, book);
            return book;
        } else {
            return null;
        }
    }

    @Override
    public void deleteBook(Long id) {
        bookMap.remove(id);
    }
}
