package com.example.library.controller;

import com.example.library.model.Books;
import com.example.library.model.User;
import com.example.library.repos.BooksRepo;
import com.example.library.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AllBooksController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BooksRepo booksRepo;

    @PostMapping("/books")
    public List<Books> like(
                          @RequestBody Books books, @AuthenticationPrincipal User user){
        books.setAuthor(user);
        booksRepo.save(books);

        return entityManager.createQuery(
                "select b from Books b join fetch b.author a ", Books.class)
                .getResultList();
    }

    @GetMapping("/list")
    public List<Books> books(){
        return entityManager.createQuery(
                "select b from Books b join fetch b.author a ", Books.class)
                .getResultList();
    }

    @GetMapping("/books/{id}")
    public Books book(@PathVariable("id") Long id ){
        return entityManager.createQuery(
                "select b from Books b join fetch b.author a where b.id = :id", Books.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @DeleteMapping("/list/{bookId}")
    public ResponseEntity<String> addBook(@PathVariable Long bookId) {
        booksRepo.deleteById(bookId);
        return new ResponseEntity<>("Delete", HttpStatus.OK);
    }
}
