package top.arron206.whisper.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import top.arron206.whisper.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(int id);
    User findByAccount(String account);
}