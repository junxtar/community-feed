package kr.amc.amis.message.application.interfaces;

import kr.amc.amis.user.domain.User;

public interface MessageRepository {

    void sendLikeMessage(User sendUser, User targetUser);
}
