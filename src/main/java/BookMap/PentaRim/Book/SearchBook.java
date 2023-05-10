package BookMap.PentaRim.Book;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class SearchBook {

    private String title;
    @JsonProperty("authors")
    private List<String> author;
    private String publisher;
    private String isbn;
    @JsonProperty("thumbnail")
    private String image;
}
