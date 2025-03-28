package kr.amc.amis.user.repository;

import java.util.List;
import kr.amc.amis.user.application.interfaces.UserRelationRepository;
import kr.amc.amis.user.domain.User;
import kr.amc.amis.user.repository.entity.UserEntity;
import kr.amc.amis.user.repository.entity.UserRelationEntity;
import kr.amc.amis.user.repository.entity.UserRelationIdEntity;
import kr.amc.amis.user.repository.jpa.JpaUserRelationRepository;
import kr.amc.amis.user.repository.jpa.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserRelationRepositoryImpl implements UserRelationRepository {

    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserRepository jpaUserRepository;

    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        UserRelationIdEntity userRelationIdEntity = new UserRelationIdEntity(user.getId(),
                targetUser.getId());

        return jpaUserRelationRepository.existsById(userRelationIdEntity);
    }

    @Override
    @Transactional
    public void save(User user, User targetUser) {
        UserRelationEntity userRelationEntity = new UserRelationEntity(user.getId(),
                targetUser.getId());
        jpaUserRelationRepository.save(userRelationEntity);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
    }

    @Override
    @Transactional
    public void delete(User user, User targetUser) {
        UserRelationIdEntity userRelationIdEntity = new UserRelationIdEntity(user.getId(),
                targetUser.getId());
        jpaUserRelationRepository.deleteById(userRelationIdEntity);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
    }
}
