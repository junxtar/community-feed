package kr.amc.amis.acceptance.utils;

import static kr.amc.amis.acceptance.steps.UserAcceptanceSteps.createUser;
import static kr.amc.amis.acceptance.steps.UserAcceptanceSteps.followUser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kr.amc.amis.user.application.dto.CreateUserRequestDto;
import kr.amc.amis.user.application.dto.FollowUserRequestDto;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    @PersistenceContext
    private EntityManager entityManager;

    public void loadData() {
        CreateUserRequestDto testUser = new CreateUserRequestDto("test user", "");
        createUser(testUser);
        createUser(testUser);
        createUser(testUser);

        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(1L, 3L));
    }

    public String getEmailToken(String email) {
        return entityManager.createNativeQuery("SELECT token FROM community_emial_verification WHERE email = ?", String.class)
                .setParameter(1, email)
                .getSingleResult()
                .toString();
    }

}
