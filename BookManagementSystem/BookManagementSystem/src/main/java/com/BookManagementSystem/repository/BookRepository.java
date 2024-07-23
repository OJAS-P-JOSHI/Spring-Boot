package com.BookManagementSystem.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.codingwallah.entity.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    List<BookEntity> findByTitle(String title);
    List<BookEntity> findByAuthor(String author);
}
