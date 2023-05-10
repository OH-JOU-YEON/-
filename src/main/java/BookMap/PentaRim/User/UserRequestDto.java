package BookMap.PentaRim.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto {

    private String nickname;
    private String state;

    private String uid;


    @Builder
    public UserRequestDto(String uid, String Nickname, String state){
        this.uid = uid;
        this.nickname = Nickname;
        this.state = state;
    }

    public User toEntity(){
        return User.builder()
                .nickname(nickname)
                .state(state)
                .build();
    }

}
