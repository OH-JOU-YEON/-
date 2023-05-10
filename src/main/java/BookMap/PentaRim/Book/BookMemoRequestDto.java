package BookMap.PentaRim.Book;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Setter
@Component
public class BookMemoRequestDto {

    private BookPersonal bookpersonal;

    private String content;

    private LocalDateTime saved;

    private Integer page;


    @Builder
    public BookMemoRequestDto(BookPersonal bookpersonal, String content, LocalDateTime localDateTime, Integer page){
        this.bookpersonal = bookpersonal;
        this.content = content;
        this.saved = localDateTime;
        this.page = page;
    }

    public BookMemo toEntity(){
        return BookMemo.builder()
                .bookPersonal(bookpersonal)
                .saved(saved)
                .content(content)
                .page(page)
                .build();
    }
}
