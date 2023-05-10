package BookMap.PentaRim.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@RunWith(SpringRunner.class)
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void 유저저장_불러오기(){
        String name = "ddd@gamil.com";
        String status = "좋음";

        userRepository.save(User.builder()
                        .nickname(name)
                        .state(status)
                        .build());

        List<User> userList = userRepository.findAll();

        User user = userList.get(0);

        assertThat(user.getNickname()).isEqualTo(name);
        assertThat(user.getState()).isEqualTo(status);

        userRepository.deleteAll();
    }



}
