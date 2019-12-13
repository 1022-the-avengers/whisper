package top.arron206.whisper.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import top.arron206.whisper.entity.Impression;
import top.arron206.whisper.entity.User;

import java.util.List;

public interface ImpressionRepository extends JpaRepository<Impression, Integer> {
    List<Impression> findByCommenterAndTarget(User Commenter, User Target);
}
