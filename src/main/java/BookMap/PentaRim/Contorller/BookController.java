package BookMap.PentaRim.Contorller;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.Book.ListBooks;
import BookMap.PentaRim.service.BookSaved;
import BookMap.PentaRim.service.BookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookSearchService bookSearchService;
    private final BookSaved bookSaved;

    //localhost:8080/booksave?id=아직은 아무거나했음&keyword=제목, isbn등등 다 가능
    @GetMapping("/booksave")
    public void BookSaved(@RequestParam String id, @RequestParam String keyword) throws Exception {
        ListBooks listbooks = bookSearchService.searchBooks(keyword);
        Book book = listbooks.getBooks().get(0);  //현재는 제일 첫번째꺼 받도록 함
        bookSaved.save(id, book);
    }
    //localhost:8080/booksave?id=아직은 아무거나했음&isbn=뭐뭐&state=done/wish/not/reading중 하나로
    @GetMapping("/booksave/changestate")
    public void ChangeBookState(@RequestParam String id, @RequestParam String isbn, @RequestParam String state) throws Exception{
        bookSaved.ChangeBookState(id, isbn, state);

    }
    /*
    @GetMapping("/booksave/complete")
    public void readcomplete(@RequestParam String id, @RequestParam String isbn) throws Exception{  //isbn으로 일단 가져오기
        bookSaved.ReadComplete(id, isbn);
    }

    @GetMapping("/booksave/want")
    public void want(@RequestParam String id, @RequestParam String isbn) throws Exception{  //isbn으로 일단 가져오기
        bookSaved.want(id, isbn);
    }

     */
}
