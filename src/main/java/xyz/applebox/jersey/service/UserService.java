package xyz.applebox.jersey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public List<UserValue.UserSimpleData> findAll() {
        return userRepository.findAll().stream().map(UserValue.UserSimpleData::of).collect(Collectors.toList());
    }

    public UserValue.UserData findById(long id) {
        return userRepository.findById(id).map(UserValue.UserData::of)
                .orElseThrow(() -> new BadRequestException("조회되는 User가 없습니다."));
    }

}
