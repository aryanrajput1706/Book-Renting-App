package com.accolite.demo.Database.Repository;

import com.accolite.demo.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByTitleContaining(String title);
    List<Book> findByGenre(String genre);
}
