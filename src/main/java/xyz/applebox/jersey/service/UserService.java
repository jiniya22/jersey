package xyz.applebox.jersey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.applebox.jersey.domain.entity.User;
import xyz.applebox.jersey.domain.value.UserValue;
import xyz.applebox.jersey.repository.UserRepository;

import javax.ws.rs.BadRequestException;
import java.util.List;
import java.util.stream.Collectors;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<UserValue.Response.UserSimpleData> findAll() {
        return userRepository.findAll().stream().map(UserValue.Response.UserSimpleData::of).collect(Collectors.toList());
    }

    @Transactional
    public Long save(UserValue.Request.UserNB data) {
        User user = User.of(data);
        userRepository.save(user);
        return user.getId();
    }

    public UserValue.Response.UserData findById(long id) {
        return userRepository.findById(id).map(UserValue.Response.UserData::of)
                .orElseThrow(() -> new BadRequestException("조회되는 User가 없습니다."));
    }

}
