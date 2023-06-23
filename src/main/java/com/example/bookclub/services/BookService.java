package com.example.bookclub.services;

import com.example.bookclub.models.Book;
import com.example.bookclub.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    // returns all the books
    public List<Book> allBook() {
        return bookRepository.findAll();
    }
    // creates a book
    public Book createBook(Book b) {
        return bookRepository.save(b);
    }
//    NEW TEST METHOD
//    public Book updateBook(Long id, Book b){
//        return bookRepository.updateBookById(id, b);
//    }
    // deletes a book
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
        return;
    }
    // retrieves a book
    public Book findbook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }
}
