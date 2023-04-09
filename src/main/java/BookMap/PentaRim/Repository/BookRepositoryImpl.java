package BookMap.PentaRim.Repository;

import BookMap.PentaRim.Book.Book;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BookRepositoryImpl implements BookRepository{

    private final static Map<String, Book> Bookstore = new ConcurrentHashMap<>();  //string으로 바꿨습니다.

    @Override
    public void save(Book book) {
        Bookstore.put(book.getIsbn(), book);
    }

    @Override
    public Book findByIsbn(String isbn) {  //string 변경함
        return Bookstore.get(isbn);
    }
}
