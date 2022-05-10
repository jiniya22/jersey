package xyz.applebox.jersey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.applebox.jersey.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
