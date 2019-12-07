package top.arron206.whisper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.arron206.whisper.dao.FriendshipRepository;
import top.arron206.whisper.entity.User;
import top.arron206.whisper.service.RelationshipService;

@Service
public class RelationshipServiceImpl implements RelationshipService {
    @Autowired
    FriendshipRepository friendshipRepository;

    public boolean isFriend(User customer, User host){
        return friendshipRepository.findByCustomAndAndHost(host, customer) != null;
    }
}
