package xyz.applebox.jersey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.applebox.jersey.domain.entity.User;
import xyz.applebox.jersey.domain.value.UserValue;
import xyz.applebox.jersey.repository.UserRepository;
import xyz.applebox.jersey.util.MessageUtils;

import javax.ws.rs.BadRequestException;
import java.util.List;
import java.util.stream.Collectors;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<UserValue.Response.Simple> findAll() {
        return userRepository.findAll().stream()
                .map(UserValue.Response.Simple::of).collect(Collectors.toList());
    }

    @Transactional
    public Long save(UserValue.Request.Creation data) {
        User user = User.of(data);
        userRepository.save(user);
        return user.getId();
    }

    public UserValue.Response.Detail findById(long id) {
        return UserValue.Response.Detail.of(getUser(id));
    }

    @Transactional
    public void patch(Long id, UserValue.Request.Patch data) {
        User user = getUser(id);
        user.patch(data);
        userRepository.save(user);
    }

    User getUser(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(MessageUtils.INVALID_USER));
    }
}
