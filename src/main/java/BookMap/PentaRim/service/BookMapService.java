package BookMap.PentaRim.service;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.BookMap.BookMap;
import BookMap.PentaRim.Hashtag.Hashtag;
import BookMap.PentaRim.memo.Memo;



import java.util.ArrayList;
import java.util.List;


public interface BookMapService {

    BookMap createBookMap(Book book);

    void addBook(ArrayList<Book> map, Book book);

    ArrayList<Book> addMap(BookMap bookMap);

    void addMemo(BookMap bookMap, Memo memo);

    void modiMap(BookMap bookMap, int index, Book book);

    void modiMemo(BookMap bookMap, int index, Memo memo);

    String checkType(BookMap bookMap, int index);

    void deleteBookMap(BookMap bookMap);

    void deleteObj(BookMap bookMap, BookMap.MapAndMemo mapAndMemo, int index);

    void changeMapIndex(ArrayList<Book> map, int inputIndex, int outIndex);

    void changeBookMapIndex(BookMap bookMap, int inputIndex, int outIndex);

    void addHashTag(BookMap bookMap, List<String> tags);

}
