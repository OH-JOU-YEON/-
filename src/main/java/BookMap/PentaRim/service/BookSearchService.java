package BookMap.PentaRim.service;

import BookMap.PentaRim.Book.ListBooks;
import org.springframework.stereotype.Component;
import java.io.IOException;


@Component
public interface BookSearchService {
    ListBooks searchBooks(String keyword) throws IOException;

}
