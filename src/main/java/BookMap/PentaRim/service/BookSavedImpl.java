package BookMap.PentaRim.service;

import BookMap.PentaRim.Book.BookPersonal;
import BookMap.PentaRim.Book.BookPersonalRequestDto;
import BookMap.PentaRim.Book.BookPersonalResponseDto;
import BookMap.PentaRim.Repository.BookPersonalRepository;
import BookMap.PentaRim.Repository.BookRepository;
import BookMap.PentaRim.User.User;
import BookMap.PentaRim.User.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookSavedImpl implements BookSaved{

    final UserRepository userRepository;
    final BookRepository bookRepository;
    final BookPersonalRepository bookPersonalRepository;


    @Override
    @Transactional
    public void Reading(Long id, BookPersonalRequestDto bookPersonalRequestDto) {
        User user = userRepository.findById(id)
                        .orElseThrow(() -> new
                IllegalArgumentException("해당 사용자가 업습니다. id = " + id));
        bookRepository.save(bookPersonalRequestDto.getBook());
        bookPersonalRequestDto.setUser(user);
        bookPersonalRepository.save(bookPersonalRequestDto.toEntity());
    }

    @Override
    @Transactional
    public List<BookPersonalResponseDto> findByUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 업습니다. id = " + id));
        List<BookPersonal> list = bookPersonalRepository.findByUser(user);
        List<BookPersonalResponseDto> bookPersonalResponseDtoList = new ArrayList<>();
        for(BookPersonal bookPersonal: list){
           bookPersonalResponseDtoList.add(new BookPersonalResponseDto(bookPersonal));
        }
        return bookPersonalResponseDtoList;
    }


}
