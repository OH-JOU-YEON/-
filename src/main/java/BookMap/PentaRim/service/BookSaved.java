package BookMap.PentaRim.service;


import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.Book.BookPersonalRequestDto;
import BookMap.PentaRim.Book.BookPersonalResponseDto;
import jakarta.transaction.Transactional;

import java.util.List;

// 저장소와 연결하고 구현? 아니면 그냥 구현?
public interface BookSaved {

    void Reading(Long id, BookPersonalRequestDto bookPersonalRequestDto);

    List<BookPersonalResponseDto> findByUser(Long id);

    //void ReadComplete();
    //void ReadMemo();
}
