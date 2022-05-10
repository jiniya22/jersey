package xyz.applebox.jersey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.applebox.jersey.domain.entity.User;
import xyz.applebox.jersey.domain.exception.InvalidRequestException;
import xyz.applebox.jersey.repository.UserRepository;

import java.util.List;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new InvalidRequestException("조회되는 User가 없습니다."));
    }

}
