package BookMap.PentaRim.Repository;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.Book.BookPersonal;
import BookMap.PentaRim.Book.BookPersonalRequestDto;
import BookMap.PentaRim.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookPersonalRepository extends JpaRepository<BookPersonal, Long> {
    List<BookPersonal> findByUser(User user);

}
