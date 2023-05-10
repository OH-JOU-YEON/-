package BookMap.PentaRim.User;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    /*
    @Column(nullable = false, length = 30, unique = true)
    private String username;
    */

    @Column(nullable = false, unique = true)
    private String nickname;
    /*

    @Column(length = 100)
    private String password;

     */
/*
    @Column(nullable = false, length = 50)
    private String email;


 */
    //@Enumerated(EnumType.STRING)
    //@Column(nullable = false)
    //private Role role;

    @Column(length = 100)
    private String state;

    @Builder
    public User(String nickname, String state){
        this.nickname = nickname;
        this.state = state;
    }

    /*
    //회원정보 수정 메서드
    public void modify(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }


     */
    public void modifyNameAndState(String nickname, String state){
        this.nickname = nickname;
        this.state = state;
    }

    /*

    //소셜 로그인 시 이미 등록된 회원이라면 수정날짜만 업데이트하고 기존 데이터보존
    public User updateModifiedDate() {
        this.onPreUpdate();
        return this;
    }

    public String getRoleValue() {
        return this.role.getValue();
    }

     */


}

