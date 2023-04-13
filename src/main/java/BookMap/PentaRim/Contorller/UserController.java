package BookMap.PentaRim.Contorller;

import BookMap.PentaRim.Repository.UserRepository;
import BookMap.PentaRim.User.User;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/insertuser")
    public void insertUser(@RequestParam String uid) throws FirebaseAuthException {
        userRepository.save(uid);
    }

    @GetMapping("getuser")
    public void getUser(@RequestParam String uid) throws Exception {
        User user = userRepository.findById(uid);
        System.out.println(user.getId());
        System.out.println(user.getNickName());
    }
}
