package BookMap.PentaRim.Repository;

import BookMap.PentaRim.User.User;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.stereotype.Component;


@Component
public interface UserRepository {

    void save(String uid) throws FirebaseAuthException;

    User findById(String id) throws Exception;
}
