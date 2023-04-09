package BookMap.PentaRim.Book;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private String title;
    @JsonProperty("authors")
    private List<String> author;
    private String publisher;
    private List<String> hashTag;
    @JsonProperty("datetime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )
    private Date publishedDay;
    private String isbn;  //카카오 검색 api의 isbn이 string으로 제공함
    @JsonProperty("thumbnail")
    private String image;

    private Long page;

}
