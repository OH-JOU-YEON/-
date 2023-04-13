package BookMap.PentaRim.User;


import BookMap.PentaRim.memo.Memo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class User {

    private String id;
    private String NickName;
    private List<Memo> memoList;  //일반 메모

    //private String 상태메세지
}
