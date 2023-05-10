package BookMap.PentaRim.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String nickname;
    private String state;

    @Builder
    public UserUpdateRequestDto(String nickname, String state){
        this.nickname = nickname;
        this.state = state;
    }
}
