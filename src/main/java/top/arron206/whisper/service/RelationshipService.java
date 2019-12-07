package top.arron206.whisper.service;

import org.springframework.stereotype.Service;
import top.arron206.whisper.entity.User;


public interface RelationshipService {
    boolean isFriend(User customer, User host);
}
