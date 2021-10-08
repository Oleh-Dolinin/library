package com.example.library.controller;

import com.example.library.model.Books;
import com.example.library.model.User;
import com.example.library.repos.BooksRepo;
import com.example.library.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class NewBookController {

    @Autowired
    private UserRepo userRepo;

    private BooksRepo booksRepo;

    public NewBookController(BooksRepo booksRepo) {
        this.booksRepo = booksRepo;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBook(
                          @RequestBody Books books, @AuthenticationPrincipal User user){

            books.setAuthor(user);
            booksRepo.save(books);

            return new ResponseEntity<>("Save", HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<String> updateBook(
            @RequestBody Books books, @AuthenticationPrincipal User user){

        books.setAuthor(user);
        booksRepo.save(books);

        return new ResponseEntity<>("Save", HttpStatus.OK);
    }
}
