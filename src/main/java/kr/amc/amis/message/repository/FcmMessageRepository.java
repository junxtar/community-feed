package kr.amc.amis.message.repository;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import java.util.Optional;
import kr.amc.amis.message.application.interfaces.MessageRepository;
import kr.amc.amis.message.repository.entity.FcmTokenEntity;
import kr.amc.amis.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FcmMessageRepository implements MessageRepository {

    private final JpaFcmTokenRepository jpaFcmTokenRepository;

    private static final String LIKE_MESSAGE_TEMPLATE = "%s님이 %s님 글에 좋아요를 눌렀습니다.";
    private static final String MESSAGE_KEY = "message";

    @Override
    public void sendLikeMessage(User sendUser, User targetUser) {
        Optional<FcmTokenEntity> tokenEntity = jpaFcmTokenRepository.findById(targetUser.getId());

        if (tokenEntity.isEmpty()) {
            return;
        }

        FcmTokenEntity fcmTokenEntity = tokenEntity.get();
        Message message = Message.builder()
                .putData(MESSAGE_KEY, LIKE_MESSAGE_TEMPLATE.formatted(sendUser.getUserName(),
                        targetUser.getUserName()))
                .setToken(fcmTokenEntity.getFcmToken())
                .build();

        FirebaseMessaging.getInstance().sendAsync(message);
    }
}
