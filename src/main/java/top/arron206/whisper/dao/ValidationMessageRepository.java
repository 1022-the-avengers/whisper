package top.arron206.whisper.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import top.arron206.whisper.dao.impl.CountValidationMessage;
import top.arron206.whisper.entity.User;
import top.arron206.whisper.entity.Validation;

public interface ValidationMessageRepository extends JpaRepository<Validation, Integer>, CountValidationMessage {
    Validation findBySenderAndReceiverAndStatus(User sender, User receiver, int status);

}
