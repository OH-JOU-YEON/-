package BookMap.PentaRim.service;

import BookMap.PentaRim.Book.BookMemoRequestDto;

public interface BookMemoService {
    void save(Long id, BookMemoRequestDto bookMemoRequestDto);
}
