package com.practice.onetoone.service.onetomany;

import com.practice.onetoone.entity.onetomany.Auther;
import com.practice.onetoone.entity.onetomany.Book;
import com.practice.onetoone.repository.onetomany.AutherRepository;
import com.practice.onetoone.repository.onetomany.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutherService {

    @Autowired
    private AutherRepository autherRepository;

    @Autowired
    private BookRepository bookRepository;

    public Auther saveAuther(Auther auther) {
        if (auther.getBooks() != null) {
            for (Book book : auther.getBooks()) {
                book.setAuther(auther);
            }
        }
        return autherRepository.save(auther);
    }

    public List<Auther> getAllAuthers() {
        return autherRepository.findAll();
    }

    public Optional<Auther> getAutherById(Integer id) {
        return autherRepository.findById(id);
    }

    public boolean deleteAutherById(Integer id) {
        if (autherRepository.existsById(id)) {
            autherRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Auther updateAuther(Integer id, Auther updatedAuther) {
        Optional<Auther> existing = autherRepository.findById(id);
        if (existing.isPresent()) {
            Auther auther = existing.get();
            auther.setName(updatedAuther.getName());

            if (updatedAuther.getBooks() != null) {
                for (Book book : updatedAuther.getBooks()) {
                    book.setAuther(auther);
                }
                auther.setBooks(updatedAuther.getBooks());
            }

            return autherRepository.save(auther);
        }
        return null;
    }

    public Book addBookToAuther(Integer autherId, Book book) {
        Optional<Auther> autherOptional = autherRepository.findById(autherId);
        if (autherOptional.isPresent()) {
            book.setAuther(autherOptional.get());
            return bookRepository.save(book);
        }
        return null;
    }

    public boolean removeBook(Integer bookId) {
        if (bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
            return true;
        }
        return false;
    }
}
