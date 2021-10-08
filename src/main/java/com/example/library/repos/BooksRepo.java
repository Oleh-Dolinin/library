package com.example.library.repos;

import com.example.library.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepo extends JpaRepository<Books, Long> {
}
