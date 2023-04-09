package BookMap.PentaRim.service;

import BookMap.PentaRim.Book.Book;
import org.springframework.stereotype.Component;

@Component
public interface BookSaved {


    void save(String id ,Book book) throws Exception;
    void ChangeBookState(String id, String isbn, String state) throws Exception;
    //void ReadMemo();

    /*
    void ReadComplete(String id, String isbn) throws Exception;
    void want(String id, String isbn) throws Exception;
    void Reading(String id, String isbn) throws Exception;

     */


}
