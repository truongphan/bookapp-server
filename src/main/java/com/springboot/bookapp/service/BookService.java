package com.springboot.bookapp.service;

import com.springboot.bookapp.model.Book;
import com.springboot.bookapp.repository.BookRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll().stream().sorted(Comparator.comparing(Book::getId)).collect(Collectors.toList());
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    public void update(Long id, Book book) throws NotFoundException {
        Book bookOld = bookRepository.findById(id).get();
        if (bookOld == null) {
            throw new NotFoundException("Not found book id: " + id);
        }
        Book bookNew = Book.builder().id(id).title(book.getTitle()).author(book.getAuthor()).build();
        bookRepository.save(bookNew);
    }

    public void delete(Long id) throws NotFoundException{
        Book book = bookRepository.findById(id).get();
        if (book == null) {
            throw new NotFoundException("Not found book id: " + id);
        }
        bookRepository.delete(book);
    }
}
