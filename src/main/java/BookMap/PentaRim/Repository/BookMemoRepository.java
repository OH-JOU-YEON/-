package BookMap.PentaRim.Repository;

import BookMap.PentaRim.Book.BookMemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMemoRepository extends JpaRepository<BookMemo, Long> {
}
