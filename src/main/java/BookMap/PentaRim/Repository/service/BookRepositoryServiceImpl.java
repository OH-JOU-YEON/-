package BookMap.PentaRim.Repository.service;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.Repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookRepositoryServiceImpl {

    private final BookRepository bookRepository;

    public BookRepositoryServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public void join(Book book) {
        bookRepository.save(book);
    }
    public Optional<Book> findBookMap(Long id) {
        return bookRepository.findById(id);
    }
}
