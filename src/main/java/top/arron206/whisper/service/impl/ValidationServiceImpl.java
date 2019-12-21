package top.arron206.whisper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.arron206.whisper.dao.FriendshipRepository;
import top.arron206.whisper.dao.ValidationMessageRepository;
import top.arron206.whisper.entity.Friendship;
import top.arron206.whisper.entity.User;
import top.arron206.whisper.entity.Validation;
import top.arron206.whisper.service.RelationshipService;
import top.arron206.whisper.service.UserService;
import top.arron206.whisper.service.ValidationService;
import top.arron206.whisper.vo.ValidationMessage;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ValidationServiceImpl implements ValidationService {
    @Autowired
    ValidationMessageRepository validationMessageRepository;
    @Autowired
    RelationshipService relationshipService;
    @Autowired
    FriendshipRepository friendshipRepository;
    @Autowired
    UserService userService;

    public boolean sendValidation(ValidationMessage validationMessage) {
        userService.setUser();
        User sender = userService.getUser();
        User receiver = new User(validationMessage.getId());
        if (relationshipService.isFriend(sender, receiver) ||
                validationMessageRepository.findBySenderAndReceiverAndStatus(sender, receiver, 1) != null ||
                validationMessageRepository.findBySenderAndReceiverAndStatus(receiver, sender, 1) != null ||
                (sender.getId() == validationMessage.getId()))
            return false;
        else {
            //发送方已读先置为true，当接收方处理后设为false，当发送方阅读后再设为true。
            Validation validation = new Validation(sender, receiver, validationMessage.getContent(), 1, true);
            this.validationMessageRepository.save(validation);
            return true;
        }
    }

    public void confirmValidation(ValidationMessage validationMessage) {
        Validation validation = validationMessageRepository.findById(validationMessage.getId()).get();
        if (validation.getStatus() == 1) {
            validation.setStatus(validationMessage.getStatus());
            //接收方处理后，发送方设为未读状态。
            validation.setSenderIfRead(false);
            validationMessageRepository.save(validation);
            if (validationMessage.getStatus() == 2) {
                //添加好友
                Friendship friendship_1 = new Friendship("默认分组",
                        validation.getSender(), validation.getReceiver());
                Friendship friendship_2 = new Friendship("默认分组",
                        validation.getReceiver(), validation.getSender());
                friendshipRepository.save(friendship_1);
                friendshipRepository.save(friendship_2);
            }
        }
    }

    public void readValidation() {
        userService.setUser();
        User currentUser = userService.getUser();
        validationMessageRepository.readMessage(currentUser.getId());
    }

    public Map<String, List<ValidationMessage>> getValidations() {
        userService.setUser();
        User currentUser = userService.getUser();
        Map<String, List<ValidationMessage>> map = new LinkedHashMap<>();
        //获取当前用户作为发送方时的验证消息。
        List<Validation> validations = currentUser.getSenderValidations();
        List<ValidationMessage> validationMessages = new ArrayList<>();
        for (Validation validation : validations)
            validationMessages.add(new ValidationMessage(validation.getId(),
                    validation.getReceiver(),
                    validation.getContent(),
                    validation.getStatus()));
        map.put("sender", validationMessages);
        //获取当前用户作为接收方时的验证消息。
        validations = currentUser.getReceiverValidations();
        validationMessages = new ArrayList<>();
        for (Validation validation : validations) {
            if (validation.getStatus() == 1) {
                validationMessages.add(new ValidationMessage(validation.getId(),
                        validation.getSender(),
                        validation.getContent(),
                        validation.getStatus()));
            }
        }
        map.put("receiver", validationMessages);
        return map;
    }

    public int getUnprocessedValidationAmount() {
        userService.setUser();
        User currentUser = userService.getUser();
        return validationMessageRepository.
                countUnprocessedOrUnreadMessage(currentUser.getId());
    }
}
