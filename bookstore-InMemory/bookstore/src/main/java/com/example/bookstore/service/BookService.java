package com.example.bookstore.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.bookstore.model.Book;

@Service
public class BookService {
    private final Map<Integer, Book> bookMap = new HashMap<>();
    private int bookId = 0;

    public List<Book> getAllBooks() {
        return new ArrayList<>(bookMap.values());
    }

    public Optional<Book> getBookById(Integer id) {
        return Optional.ofNullable(bookMap.get(id));
    }

    public Book saveBook(Book book) {
        if (book.getId() == null) {
            book.setId(++bookId);
        }
        bookMap.put(book.getId(), book);
        return book;
    }

    public void deleteBook(Integer id) {
        bookMap.remove(id);
    }
}
