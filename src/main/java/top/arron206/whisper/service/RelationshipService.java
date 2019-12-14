package top.arron206.whisper.service;

import org.springframework.stereotype.Service;
import top.arron206.whisper.dto.UserInformation;
import top.arron206.whisper.entity.User;
import top.arron206.whisper.vo.RelationshipMessage;

import java.util.List;
import java.util.Map;


public interface RelationshipService {
    boolean isFriend(User customer, User host);

    void saveRelationShip(RelationshipMessage relationshipMessage);

    void deleteRelationShip(int customId);

    Map<String, List<UserInformation>> getRelationShips();
}
