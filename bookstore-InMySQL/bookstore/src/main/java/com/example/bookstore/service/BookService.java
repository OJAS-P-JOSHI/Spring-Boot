package com.example.bookstore.service;

import java.util.List;

import com.example.bookstore.model.Book;

public interface BookService {
    Book createBook(Book book);
    Book getBookById(Long id);
    List<Book> getAllBooks();
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
}
