package com.accolite.demo.Service;

import com.accolite.demo.Model.Book;
import com.accolite.demo.Database.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id){
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not find"));
    }

    public List<Book> searchBooks(String keyword){
        return bookRepository.findByTitleContaining(keyword);
    }

    public List<Book> getBooksByGenre(String genre){
        return bookRepository.findByGenre(genre);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
}
