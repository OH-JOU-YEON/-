package BookMap.PentaRim.Book;


import jakarta.persistence.Enumerated;
import org.springframework.stereotype.Component;


public enum BookState {
    NOT, DONE, READING, WISH //기본(안읽은, 즉 저장안함), 읽은, 읽는 중인, 읽고 싶은
}
