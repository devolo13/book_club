package com.example.bookclub.controllers;

import com.example.bookclub.models.Book;
import com.example.bookclub.models.LoginUser;
import com.example.bookclub.models.User;
import com.example.bookclub.services.UserService;
import com.example.bookclub.services.BookService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class view {
    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session){
        User loginUser = userService.login(newLogin, result);
        if(result.hasErrors()){
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        session.setAttribute("userId", loginUser.getId());
        session.setAttribute("userName", loginUser.getName());
        return "redirect:/books";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session){
        User registeredUser = userService.register(newUser, result);
        if(result.hasErrors()){
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        session.setAttribute("userId",registeredUser.getId());
        session.setAttribute("userName",registeredUser.getName());
        return "redirect:/books";
    }

    @GetMapping("/books")
    public String books(HttpSession session, Model model){
        if (session.getAttribute("userId")  == null){
            session.invalidate();
            return "redirect:/";
        }
        List<Book> allBooks = bookService.allBook();
        model.addAttribute("allBooks", allBooks);
        return "allBooks.jsp";
    }

    @GetMapping("/books/new")
    public String newBook(HttpSession session, @ModelAttribute("book") Book book){
        if (session.getAttribute("userId")  == null){
            session.invalidate();
            return "redirect:/";
        }
        return "newBook.jsp";
    }

    @PostMapping("/books/add")
    public String createBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            model.addAttribute("book", new Book());
            return "newBook.jsp";
        }
        bookService.createBook(book);
        return "redirect:/books";
    }

    @GetMapping("/books/{book_id}")
    public String viewBook(HttpSession session, Model model, @PathVariable("book_id") long book_id){
        if (session.getAttribute("userId")  == null){
            session.invalidate();
            return "redirect:/";
        }
        Book book = bookService.findbook(book_id);
        model.addAttribute("book", book);
        return "oneBook.jsp";
    }

    @GetMapping("/books/{book_id}/edit")
    public String editBook(HttpSession session, Model model, @PathVariable("book_id") long book_id){
        if (session.getAttribute("userId")  == null){
            session.invalidate();
            return "redirect:/";
        }
        Book book = bookService.findbook(book_id);
        model.addAttribute("book", book);
        return "editBook.jsp";
    }

    @PutMapping("/books/edit/{id}")
    public String saveEditedBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            model.addAttribute("book", book);
            return "editBook.jsp";
        }
        Book newBook = bookService.createBook(book);
        return "redirect:/books/" + book.getId();
    }

    @GetMapping("/books/{book_id}/delete")
    public String deleteBook(@PathVariable("book_id") Long book_id ,HttpSession session){
        bookService.deleteBook(book_id);
        return "redirect:/books";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
