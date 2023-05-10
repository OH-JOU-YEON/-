package BookMap.PentaRim.service;

import BookMap.PentaRim.User.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Long save(UserRequestDto userRequestDto){
        return userRepository.save(userRequestDto.toEntity()).getId();
    }
    @Override
    @Transactional
    public Long update(Long id, UserUpdateRequestDto requestDto){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new
                IllegalArgumentException("해당 사용자가 업습니다. id = " + id));
        user.modifyNameAndState(requestDto.getNickname(), requestDto.getState());

        return id;
    }

    @Override
    public UserResponseDto findByID(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 유저가 없습니다. id = " + id));
        return new UserResponseDto(user);
    }

    /*
    @Override
    public Long findByUID(String uid){
        User user = userRepository.findByUid(uid);
        return user.getId();
    }

     */
}
