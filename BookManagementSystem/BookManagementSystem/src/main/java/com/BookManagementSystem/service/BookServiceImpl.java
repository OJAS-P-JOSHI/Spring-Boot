package com.BookManagementSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.BookManagementSystem.entity.BookEntity;
import com.BookManagementSystem.model.Book;
import com.BookManagementSystem.repository.BookRepository;

@Service
class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        List<BookEntity> booksDB = bookRepository.findAll();
        for (BookEntity bookEntity : booksDB) {
            Book book = new Book();
            BeanUtils.copyProperties(bookEntity, book);
            books.add(book);
        }
        return books;
    }

    @Override
    public Book getBookById(int id) {
        Optional<BookEntity> bookEntityOpt = bookRepository.findById(id);
        if (bookEntityOpt.isPresent()) {
            Book book = new Book();
            BeanUtils.copyProperties(bookEntityOpt.get(), book);
            return book;
        } else {
            throw new RuntimeException("Book not found for id: " + id);
        }
    }

    @Override
    public Book saveBook(Book book) {
        BookEntity bookEntity = new BookEntity();
        BeanUtils.copyProperties(book, bookEntity);
        // Ensure book with the same ID doesn't already exist
        if (bookRepository.existsById(book.getId())) {
            throw new RuntimeException("Book with id " + book.getId() + " already exists.");
        }
        bookRepository.save(bookEntity);
        return book;
    }

    @Override
    public String deleteBook(int id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return "Deleted successfully";
        } else {
            throw new RuntimeException("Book not found for id: " + id);
        }
    }

    @Override
    public Book updateMyBook(int id, Book book) {
        Optional<BookEntity> bookEntityOpt = bookRepository.findById(id);
        if (bookEntityOpt.isPresent()) {
            BookEntity bookEntity = bookEntityOpt.get();
            bookEntity.setAuthor(book.getAuthor());
            bookEntity.setTitle(book.getTitle());
            bookRepository.save(bookEntity);
            BeanUtils.copyProperties(bookEntity, book);
            return book;
        } else {
            throw new RuntimeException("Book not found for id: " + id);
        }
    }

    @Override
    public List<Book> findBooksByTitle(String title) {
        List<Book> books = new ArrayList<>();
        List<BookEntity> bookEntities = bookRepository.findByTitle(title);
        for (BookEntity bookEntity : bookEntities) {
            Book book = new Book();
            BeanUtils.copyProperties(bookEntity, book);
            books.add(book);
        }
        return books;
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        List<Book> books = new ArrayList<>();
        List<BookEntity> bookEntities = bookRepository.findByAuthor(author);
        for (BookEntity bookEntity : bookEntities) {
            Book book = new Book();
            BeanUtils.copyProperties(bookEntity, book);
            books.add(book);
        }
        return books;
    }
}
