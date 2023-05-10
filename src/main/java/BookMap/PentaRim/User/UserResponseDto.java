package BookMap.PentaRim.User;

import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long id;
    private String nickname;
    private String state;

    public UserResponseDto(User entity){
        this.id = entity.getId();
        this.nickname = entity.getNickname();
        this.state = entity.getState();
    }
}
