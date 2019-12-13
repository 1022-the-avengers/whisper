package top.arron206.whisper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.arron206.whisper.dao.ImpressionRepository;
import top.arron206.whisper.dao.UserRepository;
import top.arron206.whisper.entity.Impression;
import top.arron206.whisper.entity.User;
import top.arron206.whisper.service.ImpressionService;
import top.arron206.whisper.vo.ImpressionMessage;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImpressionServiceImpl implements ImpressionService {
    @Autowired
    ImpressionRepository impressionRepository;
    @Autowired
    UserRepository userRepository;

    public void saveImpression(ImpressionMessage impressionMessage) {
        User commenter = new User();
        commenter.setId(impressionMessage.getCommenterId());
        User target = new User();
        target.setId(impressionMessage.getTargetId());
        List<Impression> preImpressions = this.impressionRepository.findByCommenterAndTarget(commenter, target);
        this.impressionRepository.deleteAll(preImpressions);
        for (String content : impressionMessage.getContents()) {
            this.impressionRepository.save(new Impression(content, commenter, target));
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
