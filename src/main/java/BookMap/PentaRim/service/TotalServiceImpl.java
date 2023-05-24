package BookMap.PentaRim.service;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.Book.BookMemo;
import BookMap.PentaRim.Book.BookPersonal;
import BookMap.PentaRim.Book.BookState;
import BookMap.PentaRim.Book.Dto.*;
import BookMap.PentaRim.BookMap.BookMapTest;
import BookMap.PentaRim.BookMap.Dto.BookMapTestResponseDto;
import BookMap.PentaRim.Dto.BookShelfResponseDto;
import BookMap.PentaRim.Dto.MainResponseDto;
import BookMap.PentaRim.Dto.ProfileResponseDto;
import BookMap.PentaRim.Repository.BookMapTestRepository;
import BookMap.PentaRim.Repository.BookMemoRepository;
import BookMap.PentaRim.Repository.BookPersonalRepository;
import BookMap.PentaRim.User.User;
import BookMap.PentaRim.User.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TotalServiceImpl implements TotalService{
    final UserRepository userRepository;
    final BookPersonalRepository bookPersonalRepository;
    final BookMapTestRepository bookMapTestRepository;
    final BookMemoRepository bookMemoRepository;
    final BookSaved bookSaved;

    @Override
    @Transactional
    public MainResponseDto main(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        List<BookPersonal> bookPersonalList = bookPersonalRepository.findTop4ByUserOrderBySavedDesc(user);
        List<Book> bookList = new ArrayList<>();
        for(BookPersonal bookPersonal: bookPersonalList){
            bookList.add(bookPersonal.getBook());
        }
        List<BookImageDto> bookImageDtos = new ArrayList<>();
        for(Book book: bookList){
            bookImageDtos.add(new BookImageDto(book));
        }
        List<BookMapTest> bookMapTests = bookMapTestRepository.findAllByUser(user);
        List<BookMapTestResponseDto> bookMapResponseDtos = new ArrayList<>();
        for(BookMapTest bookMapTest: bookMapTests) {
            bookMapResponseDtos.add(new BookMapTestResponseDto(bookMapTest.getId()));
        }
        List<BookTopResponseDto> bookTopResponseDtos = bookSaved.findByTop2();
        return new MainResponseDto(bookImageDtos, bookMapResponseDtos, bookTopResponseDtos);
    }

    @Override
    @Transactional
    public List<BookShelfResponseDto> bookshelf(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        List<BookPersonal> bookPersonalList = bookPersonalRepository.findBookPersonalsByUserOrderBySavedDesc(user);
        List<BookShelfResponseDto> bookShelfResponseDtos = new ArrayList<>();
        for(BookPersonal bookPersonal: bookPersonalList){
            if(bookPersonal.getBookState() == BookState.DONE){
                bookShelfResponseDtos.add(new BookShelfDoneDto(bookPersonal));
            }else if(bookPersonal.getBookState() == BookState.READING){
                bookShelfResponseDtos.add(new BookShelfReadingDto(bookPersonal));
            }else{
                bookShelfResponseDtos.add(new BookShelfWishDto(bookPersonal));
            }
        }
        return bookShelfResponseDtos;
    }

    @Override
    @Transactional
    public List<BookTopResponseDto> mostBooks(){
        return bookSaved.findByTop10();
    }

    @Override
    @Transactional
    public List<BookShelfResponseDto> readBooks(Long id){
        return bookshelfState(id, BookState.DONE);
    }

    @Override
    @Transactional
    public List<BookShelfResponseDto> readingBooks(Long id){
        return bookshelfState(id, BookState.READING);
    }

    @Override
    @Transactional
    public List<BookShelfResponseDto> wantBooks(Long id){
        return bookshelfState(id, BookState.WISH);
    }

    @Override
    @Transactional
    public List<BookShelfResponseDto> bookshelfState(Long id, BookState bookState){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        List<BookPersonal> bookPersonalList = bookPersonalRepository.findBookPersonalsByUserAndBookStateOrderBySaved(user, bookState);
        List<BookShelfResponseDto> bookShelfResponseDtos = new ArrayList<>();
        for(BookPersonal bookPersonal: bookPersonalList){
            bookShelfResponseDtos.add(new BookShelfDoneDto(bookPersonal));
        }
        return bookShelfResponseDtos;
    }

    @Override
    @Transactional
    public ProfileResponseDto profile(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        Integer count = bookSaved.findByMonthCount(id);
        List<BookMemo> bookMemoList = bookMemoRepository.findByBookPersonal_UserOrderBySavedDesc(user);
        List<ProfileMemoResponseDto> profileMemoResponseDtoList = new ArrayList<>();
        for(BookMemo bookMemo: bookMemoList){
            profileMemoResponseDtoList.add(new ProfileMemoResponseDto(bookMemo));
        }
        return new ProfileResponseDto(user, count, profileMemoResponseDtoList);
    }
}
