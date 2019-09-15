package com.springboot.bookapp.controller;

import com.springboot.bookapp.model.Book;
import com.springboot.bookapp.service.BookService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books/{book-id}")
    public ResponseEntity<Book> getBookById(@PathVariable("book-id") Long id) {
        return ResponseEntity.ok().body(bookService.getBookById(id));
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok().body(books);
    }

    @PostMapping("/books")
    public ResponseEntity<Book> saveBook(@RequestBody  Book book) {
        return ResponseEntity.ok().body(bookService.save(book));
    }

    @PutMapping("/books/{book-id}")
    public ResponseEntity<?> update(@PathVariable("book-id") Long id, @RequestBody Book book) throws NotFoundException {
        bookService.update(id, book);
        return ResponseEntity.ok().body("Book has been updated!");
    }

    @DeleteMapping("/books/{book-id}")
    public ResponseEntity<?> delete(@PathVariable("book-id") Long id) throws NotFoundException {
        bookService.delete(id);
        return ResponseEntity.ok().body("Book has been deleted!");
    }
}
