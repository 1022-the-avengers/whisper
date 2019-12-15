package top.arron206.whisper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.arron206.whisper.dao.ImpressionRepository;
import top.arron206.whisper.dao.UserRepository;
import top.arron206.whisper.entity.Impression;
import top.arron206.whisper.entity.User;
import top.arron206.whisper.service.ImpressionService;
import top.arron206.whisper.service.UserService;
import top.arron206.whisper.vo.ImpressionMessage;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImpressionServiceImpl implements ImpressionService {
    @Autowired
    ImpressionRepository impressionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    public void saveImpression(ImpressionMessage impressionMessage) {
        userService.setUser();
        User commenter = userService.getUser();
        if (commenter.getId() != impressionMessage.getTargetId()) {
            //清空对别人的好友印象。
            User target = new User(impressionMessage.getTargetId());
            List<Impression> preImpressions = this.impressionRepository.findByCommenterAndTarget(commenter, target);
            this.impressionRepository.deleteAll(preImpressions);
            for (String content : impressionMessage.getContents()) {
                this.impressionRepository.save(new Impression(content, commenter, target));
            }
        }
        else {
            //清空自己的好友印象。
            List<Impression> preImpressions = this.impressionRepository.findByTarget(commenter);
            this.impressionRepository.deleteAll(preImpressions);
            boolean hasAdd = false;
            for (String content : impressionMessage.getContents()) {
                for (Impression preImpression : preImpressions) {
                    if (preImpression.getContent().equals(content)) {
                        this.impressionRepository.save(new Impression(content, commenter, preImpression.getTarget()));
                        hasAdd = true;
                        break;
                    }
                }
                if (!hasAdd)
                    this.impressionRepository.save(new Impression(content, commenter, commenter));
            }
        }
    }

    public List<String> getImpressions(int id) {
        User user = this.userRepository.findById(id);
        List<String> contents = new ArrayList<>();
        for (Impression impression : user.getTargetImpression())
            contents.add(impression.getContent());
        return contents;
    }
}
