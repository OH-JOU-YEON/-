package BookMap.PentaRim.User;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApiControllerTest {

    @Autowired
    UserRepository userRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @After("")
    void delete(){
        userRepository.deleteAll();
    }

    @Test
    public void 유저_등록된다() throws Exception{
        String nickname= "닉넴닉넴";
        String state = "상태상태";
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .Nickname(nickname)
                .state(state)
                .build();

        String url = "http://localhost:" + port + "/user/register";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, userRequestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<User> userList = userRepository.findAll();
        assertThat(userList.get(0).getNickname()).isEqualTo(nickname);
        assertThat(userList.get(0).getState()).isEqualTo(state);

    }

    @Test
    public void 유저정보_수정된다() throws Exception{

        User savedUser = userRepository.save(User.builder()
                .nickname("유저유저")
                        .state("재밌다")
                .build());

        Long updateId = savedUser.getId();

        String expectedNickname = "다른닉넴";
        String expectedState = "다른상태";

        UserUpdateRequestDto userUpdateRequestDto =
                UserUpdateRequestDto.builder()
                        .nickname(expectedNickname)
                        .state(expectedState)
                        .build();

        String url = "http://localhost:" + port + "/user/update/" + updateId;

        HttpEntity<UserUpdateRequestDto> requestDtoHttpEntity = new HttpEntity<>(userUpdateRequestDto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestDtoHttpEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<User> userList = userRepository.findAll();
        assertThat(userList.get(0).getNickname()).isEqualTo(expectedNickname);
        assertThat(userList.get(0).getState()).isEqualTo(expectedState);

        userRepository.deleteAll();  //after 어노테이션해도 db에 안사라져있음
    }
}
