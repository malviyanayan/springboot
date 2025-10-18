package com.practice.onetoone.controllers.onetomany;

import com.practice.onetoone.entity.onetomany.Auther;
import com.practice.onetoone.entity.onetomany.Book;
import com.practice.onetoone.service.onetomany.AutherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authers")
public class AutherController {

    @Autowired
    private AutherService autherService;

    // Create auther with books
    @PostMapping("/add")
    public ResponseEntity<Auther> saveAuther(@RequestBody Auther auther) {
        Auther saved = autherService.saveAuther(auther);
        if (saved != null) {
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Get all authers
    @GetMapping("/all")
    public ResponseEntity<List<Auther>> getAllAuthers() {
        return new ResponseEntity<>(autherService.getAllAuthers(), HttpStatus.OK);
    }

    // Get auther by id
    @GetMapping("/{id}")
    public ResponseEntity<Auther> getAutherById(@PathVariable Integer id) {
        Optional<Auther> auther = autherService.getAutherById(id);
        return auther.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update auther
    @PutMapping("/update/{id}")
    public ResponseEntity<Auther> updateAuther(@PathVariable Integer id, @RequestBody Auther updatedAuther) {
        Auther updated = autherService.updateAuther(id, updatedAuther);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete auther by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAuther(@PathVariable Integer id) {
        boolean deleted = autherService.deleteAutherById(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Add book to auther
    @PostMapping("/{autherId}/books/add")
    public ResponseEntity<Book> addBookToAuther(@PathVariable Integer autherId, @RequestBody Book book) {
        Book saved = autherService.addBookToAuther(autherId, book);
        if (saved != null) {
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Remove a book by bookId
    @DeleteMapping("/books/delete/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer bookId) {
        boolean deleted = autherService.removeBook(bookId);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
