package top.arron206.whisper.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import top.arron206.whisper.service.impl.UserOnlineImpl;
import top.arron206.whisper.dao.UserRepository;

import java.util.Objects;

@Component
public class STOMPConnectEventListener implements ApplicationListener<SessionConnectEvent> {

    @Autowired
    UserOnlineImpl userOnlineImpl;

    @Autowired
    UserRepository userRepository;

    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        String userId = sha.getFirstNativeHeader("userId");
        assert userId != null;
        assert userRepository.findById(Integer.parseInt(userId)) != null;
        if (Objects.requireNonNull(sha.getCommand()) == StompCommand.CONNECT) {
            userOnlineImpl.connect(Integer.valueOf(userId));
        }
    }
}
