package BookMap.PentaRim.service;

import BookMap.PentaRim.Book.BookMemoRequestDto;
import BookMap.PentaRim.Book.BookPersonal;
import BookMap.PentaRim.Repository.BookMemoRepository;
import BookMap.PentaRim.Repository.BookPersonalRepository;
import BookMap.PentaRim.Repository.MemoRepository;
import BookMap.PentaRim.User.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookMemoServiceImp implements BookMemoService{
    final BookPersonalRepository bookPersonalRepository;
    final BookMemoRepository bookMemoRepository;
    final UserRepository userRepository;


    @Override
    @Transactional
    public void save(Long id, BookMemoRequestDto bookMemoRequestDto){
        BookPersonal bookPersonal = bookPersonalRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 bookpersonal이 업습니다. id = " + id));
        bookMemoRequestDto.setBookpersonal(bookPersonal);
        bookMemoRequestDto.setSaved(LocalDateTime.now());
       bookMemoRepository.save(bookMemoRequestDto.toEntity());
    }
}
