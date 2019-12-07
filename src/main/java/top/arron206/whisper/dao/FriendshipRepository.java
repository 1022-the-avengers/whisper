package top.arron206.whisper.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import top.arron206.whisper.entity.Friendship;
import top.arron206.whisper.entity.User;

public interface FriendshipRepository extends JpaRepository<Friendship, Integer>{
    Friendship findByCustomAndAndHost(User custom, User host);
}
